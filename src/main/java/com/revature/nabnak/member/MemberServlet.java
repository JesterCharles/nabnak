package com.revature.nabnak.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.nabnak.member.dto.requests.EditMemberRequest;
import com.revature.nabnak.member.dto.requests.NewRegistrationRequest;
import com.revature.nabnak.member.dto.response.MemberResponse;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import com.revature.nabnak.util.interfaces.Authable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/member")
public class MemberServlet extends HttpServlet implements Authable {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;
    //private final Logger logger = Logger.getLogger(MemberServlet.class.getName());
    private final Logger logger = LogManager.getLogger();

    // Because we are perfoming dependency injection, we can no longer use the @WebServlet above. DONT FORGET
    public MemberServlet(MemberService memberService, ObjectMapper objectMapper){
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // TODO: Issue of there being more than just a doGet for reading all, things like find a member by ID
        String email = req.getParameter("email"); // every parameter is read as a STRING
        Member authMember = (Member) req.getSession().getAttribute("authMember"); // cast the returned object to a member




        // qwhen using query params and specifying endpoints to hit parituclar crud operations, make sure you go from most specific to least
//        if (email != null && password != null){ // this has security risks with ap assword in a url
//            Member member = memberService.login(email, password);
//            String payload = objectMapper.writeValueAsString(member);
//            resp.getWriter().write(payload);
//        } else
        if(email != null) {
            logger.info("Email entered {}", email);
            try {
                MemberResponse member = memberService.findById(email);

                String payloadID = objectMapper.writeValueAsString(member);

                resp.getWriter().write(payloadID);
            } catch (InvalidUserInputException e){
                logger.warn("User information entered was not reflective of any member in the databse. Email provided was: {}", email);
                resp.getWriter().write("Email entered was not found in the database");
                resp.setStatus(404);
            }
        } else {
            List<MemberResponse> members = memberService.readAll();

            String payload = objectMapper.writeValueAsString(members); // mapper parsing from Java Object to JSON

            resp.getWriter().write(payload);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DO NOT DO LOGIN INSIDE OF YOUR MEMBER SERVLET
//        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class); // this provides the body from the request as a JSON, leveraging Reflections
//
//        Member member = memberService.login(loginCreds.getEmail(), loginCreds.getPassword());
//
//        resp.getWriter().write("Welcome back to nabnak " + member.getFullName());
        PrintWriter respWriter = resp.getWriter(); // preference play, lot of folks enjoy this
        NewRegistrationRequest member = objectMapper.readValue(req.getInputStream(), NewRegistrationRequest.class);


        try {
            logger.info("User has request to add the following to the database {}", member);
            MemberResponse newMember = memberService.registerMember(member);

            String payload = objectMapper.writeValueAsString(newMember);

            respWriter.write(payload);
            resp.setStatus(201);
        } catch (InvalidUserInputException | ResourcePersistanceException e){
            logger.warn("Exception thrown when trying to persist. Message from exception: {}", e.getMessage());
            respWriter.write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            logger.error("Something unexpected happened and this exception was thrown: {} with message: {}", e.getClass().getName(), e.getMessage());
            respWriter.write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditMemberRequest editMember = objectMapper.readValue(req.getInputStream(), EditMemberRequest.class);

        try {
            memberService.update(editMember);
            resp.getWriter().write("Member has been successfully updated");
        } catch (InvalidUserInputException e){
            resp.getWriter().write(e.getMessage());
            resp.setStatus(404);
        } catch (Exception e){
            resp.getWriter().write(e.getMessage() + " " + e.getClass().getName());
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkAuth(req, resp)) return;
        String email = req.getParameter("email");
        if(email != null){
            memberService.remove(email);
            resp.getWriter().write("Member with " + email + " has been deleted");
        } else {
            resp.getWriter().write("This request requires an email parameter in the path ?email=example@mail.com");
            resp.setStatus(400);
        }
    }


}
