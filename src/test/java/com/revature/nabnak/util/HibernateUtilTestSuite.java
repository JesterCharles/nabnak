package com.revature.nabnak.util;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static com.revature.nabnak.util.HibernateUtil.getSession;

public class HibernateUtilTestSuite {

    @Test
    public void test_getConnection_returnValidConnection_givenProvidedCredentialsAreCorrect(){
        try(Session session = getSession()) {
            System.out.println(session);
            Assertions.assertNotNull(session);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
