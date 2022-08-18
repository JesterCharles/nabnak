package com.revature.nabnak.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.nabnak.card.CardDao;
import com.revature.nabnak.card.CardService;
import com.revature.nabnak.card.CardServlet;
import com.revature.nabnak.member.MemberDao;
import com.revature.nabnak.member.MemberService;
import com.revature.nabnak.member.MemberServlet;
import com.revature.nabnak.util.web.AuthServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class ServletContext {


        public final void run() {
            // For Elastic beanstalk deployment
            new File("src/main/webapp/").mkdirs();
            new File("target/classes").mkdirs();

            String webappDirLocation = new File("src/main/webapp/").getAbsolutePath();
            String additonalClasses = new File("target/classes").getAbsolutePath();

            Tomcat tomcat = new Tomcat();

            try {
                StandardContext standardContext = (StandardContext) tomcat.addWebapp("/", webappDirLocation);
                WebResourceRoot resourceRoot = new StandardRoot(standardContext);
                resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/WEB-INF/classes", additonalClasses, "/")); // everything you need prior to build the application

                standardContext.setResources(resourceRoot); // at this point the tomcat server now has acces and knowledge of classes information

                // Left for DI for later use
                MemberDao memberDao = new MemberDao();
                MemberService memberService = new MemberService(memberDao);
                ObjectMapper objectMapper = new ObjectMapper();
                CardDao cardDao = new CardDao();
                CardService cardService = new CardService(memberService, cardDao);

               // tomcat.setPort(3000); // Do not change port from 8080, leave default. This is just to show you can alter the ports. BEcause certain cloud providers sometimes change their ports. they use just 80 or 8080

                // let's estbalish our MemberServlet inside of tomcat
                // These two ADDITIONAL STEPS ARE REQUIRED OF US FOR DEPENDENCY INJECTION
                tomcat.addServlet("", "MemberServlet", new MemberServlet(memberService, objectMapper));
                standardContext.addServletMappingDecoded("/member", "MemberServlet");

                tomcat.addServlet("", "AuthServlet", new AuthServlet(memberService, objectMapper));
                standardContext.addServletMappingDecoded("/auth", "AuthServlet");

                tomcat.addServlet("", "CardServlet", new CardServlet(objectMapper,cardService));
                standardContext.addServletMappingDecoded("/card", "CardServlet");

                tomcat.start(); // there is a default port on your computer for testing, 8080 this is a "developers port"
                tomcat.getServer().await();

            } catch (ServletException | LifecycleException e) {
                throw new RuntimeException(e);
            }
        }


}
