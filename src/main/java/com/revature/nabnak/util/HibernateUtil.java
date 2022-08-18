package com.revature.nabnak.util;

import com.revature.nabnak.board.Board;
import com.revature.nabnak.card.Card;
import com.revature.nabnak.member.Member;
import com.revature.nabnak.team.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            // Searching the thread for the file specified and streaming it into the properties.load()
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("hibernate.properties"));
//            properties.load(new FileReader("src/main/resources/hibernate.properties"));

            // ONE ADDITIONAL STEP I NEED TO INCLUDE
            configuration.addAnnotatedClass(Member.class);
            configuration.addAnnotatedClass(Card.class);
            configuration.addAnnotatedClass(Team.class);
            configuration.addAnnotatedClass(Board.class);

            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}
