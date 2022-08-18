package com.revature.nabnak.member;

import com.revature.nabnak.member.dto.requests.EditMemberRequest;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemberService {
    // Attributes

    private final MemberDao memberDao;
    private Member sessionMember = null;
    private final Logger logger = LogManager.getLogger();

    // CONSTRUCTOR
    public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    // Methods
    public MemberResponse registerMember(NewRegistrationRequest newRegistration) throws InvalidUserInputException, ResourcePersistanceException{


            Member newMember = new Member();

            newMember.setEmail(newRegistration.getEmail());
            newMember.setFullName(newRegistration.getFullName());
            newMember.setExperienceMonths(newRegistration.getExperienceMonths());
            newMember.setPassword(newRegistration.getPassword());
            // Java will set these for us
            newMember.setId(UUID.randomUUID().toString());
            newMember.setRegistrationDate(new Date(System.currentTimeMillis()));

            logger.info("Member registration service has begun with the provide: {}", newMember);
            if (!isMemberValid(newMember)) {
                throw new InvalidUserInputException("User input was invalid");
            }

            if(!isEmailAvailable(newMember.getEmail())){
                throw new ResourcePersistanceException("Email is already registered please try logging in.");
            }

            memberDao.create(newMember);

            return new MemberResponse(newMember);

    }
    // TODO: NEW READ ME (Lines 43-73)
    public Member login(String email, String password){
        Member member = memberDao.loginCredentialCheck(email, password);
        sessionMember = member;
        return member;
    }

    // TODO: NEW READ ME (Lines 76 - 105)
    public List<MemberResponse> readAll(){

        // Streams are a form of functional programming this is form a declarative programming
        List<MemberResponse> members = memberDao.findAll()
                                                .stream()//this reads through each value inside of the collection (aka our List)
                                                //.map(member -> new MemberResponse(member))
                                                // this is leveraging (::) which is know as the method reference operator, it's taking the method from MemberReponse and applying to all objects in the stream
                                                .map(MemberResponse::new)
                                                .collect(Collectors.toList());
        ;
        return members;
    }
    public MemberResponse findById(String email){

        Member member = memberDao.findById(email);
        MemberResponse responseMember = new MemberResponse(member);
        return responseMember;
    }

    public boolean isMemberValid(Member newMember){
        if(newMember == null) return false;
        // this || is the expression to signify to the conditional that if either of these are true then perform the action
        if(newMember.getEmail() == null || newMember.getEmail().trim().equals("")) return false;
        if(newMember.getFullName() == null || newMember.getFullName().trim().equals("")) return false;
        if(newMember.getExperienceMonths() < 0 ) return false;
        if(newMember.getRegistrationDate() == null || newMember.getRegistrationDate().toString().trim().equals("")) return false;
        if(newMember.getPassword() == null || newMember.getPassword().trim().equals("")) return false;
        return true;
    }

    // TODO: IMPLEMENT MEEEEEEE!!!!!!!
    public boolean isEmailAvailable(String email){
        return memberDao.checkEmail(email);
    }

    public boolean remove(String email){
        return memberDao.delete(email);
    }
    public boolean update(EditMemberRequest editMember) throws InvalidUserInputException{

       Member foundMember = memberDao.findById(editMember.getId());

       // Predicate - to evaluate a true or false given a lambda expression
        // Lambda expression (arrow notation) - a syntax for a SINGULAR function
        Predicate<String> notNullOrEmpty = (str) -> str != null && !str.trim().equals("");


        // Example of Automatic Dirty Checking
       if(notNullOrEmpty.test(editMember.getFullName())){
           foundMember.setFullName(editMember.getFullName());
        }
       if(notNullOrEmpty.test(editMember.getPassword())){
               foundMember.setPassword(editMember.getPassword());
       }
       if(notNullOrEmpty.test(editMember.getEmail())){
           if(!isEmailAvailable(editMember.getEmail())){
               throw new ResourcePersistanceException("The provided email is already registered");
           }
           foundMember.setEmail(editMember.getEmail());
       }

        return memberDao.update(foundMember);
    }

    public Member getSessionMember(){
        return sessionMember;
    }

}
