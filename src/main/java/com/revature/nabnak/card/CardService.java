package com.revature.nabnak.card;

import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.member.Member;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardService {

    private final MemberService memberService; // this now has it's own unique session
    private final CardDao cardDao;

    public CardService(MemberService memberService, CardDao cardDao){ // what's this methodology?
        this.memberService = memberService;
        this.cardDao = cardDao;

    }

    //TODO: Implement validation
     public boolean isCardValid(Card newCard){
         return false;
     }

     public CardResponse addCard(NewCardRequest cardRequest) throws InvalidUserInputException{

        areEnumsValid(cardRequest);

       // Member member = memberService.findById(cardRequest.getId());

        Card newCard = new Card(cardRequest, memberService.getSessionMember());

        return new CardResponse(cardDao.create(newCard));
     }

     public List<CardResponse> findAllCards(){
        return cardDao.findAll().stream().map(CardResponse::new).collect(Collectors.toList());
     }

     public List<Card> findAllCardsByMember(String email){
        return cardDao.findAllByUser(email);
     }

     public Card findCardById(String id){
        return cardDao.findById(id);
     }

     public Card update(Card updatedCard){
        cardDao.update(updatedCard);
        return updatedCard;
     }

     public boolean delete(String id){
        return cardDao.delete(id);
     }

     public boolean areEnumsValid(NewCardRequest cardRequest) throws InvalidUserInputException{
        List<String> techEnums = Arrays.asList("JAVA", "JAVASCRIPT", "PYTHON", "RUBY", "GO", "REACT", "TYPESCRIPT", "LUA", "CARBON");
        List<String> statusEnums = Arrays.asList("OPEN", "INPROGRESS", "CLOSED");

        List<Boolean> checkTechEnums = techEnums.stream().map(str -> str.equals(cardRequest.getTech().toUpperCase())).collect(Collectors.toList());
        if(!checkTechEnums.contains(true)){
            throw new InvalidUserInputException(
                    "Tech was not a valid entry please try the follow : " +
                    techEnums.stream().map(Object::toString).collect(Collectors.joining(",")) // this will produce all available tech enums
                    );
        }

         List<Boolean> checkStatusEnums = statusEnums.stream().map(str -> str.equals(cardRequest.getStatus().toUpperCase())).collect(Collectors.toList());
         if(!checkStatusEnums.contains(true)){
             throw  new InvalidUserInputException(
                     "Status was not a valid entry please try the follow : " +
                             statusEnums.stream().map(Object::toString).collect(Collectors.joining(",")) // this will produce all available tech enums
             );
         }
        return true;

     }

}
