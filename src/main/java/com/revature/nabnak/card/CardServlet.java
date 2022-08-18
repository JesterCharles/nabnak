package com.revature.nabnak.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.nabnak.card.dto.requests.NewCardRequest;
import com.revature.nabnak.card.dto.responses.CardResponse;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CardServlet extends HttpServlet implements Authable {

    private final ObjectMapper objectMapper;
    private final CardService cardService;
    //private final Logger logger = Logger.getLogger(MemberServlet.class.getName());
    private final Logger logger = LogManager.getLogger();

    // Because we are perfoming dependency injection, we can no longer use the @WebServlet above. DONT FORGET
    public CardServlet(ObjectMapper objectMapper, CardService cardService){
        this.objectMapper = objectMapper;
        this.cardService = cardService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req,resp)) return;
        try {
            NewCardRequest newCardRequest = objectMapper.readValue(req.getInputStream(), NewCardRequest.class);
            CardResponse card = cardService.addCard(newCardRequest);

            String payload = objectMapper.writeValueAsString(card);
            resp.getWriter().write(payload);
        } catch (InvalidUserInputException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(400);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null){
            Card card = cardService.findCardById(id);
            String payload = objectMapper.writeValueAsString(card);
            resp.getWriter().write(payload);

        } else {

            List<CardResponse> cards = cardService.findAllCards();

            String payload = objectMapper.writeValueAsString(cards);

            resp.getWriter().write(payload);
        }
    }
}
