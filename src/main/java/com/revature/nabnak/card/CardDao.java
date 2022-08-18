package com.revature.nabnak.card;

import com.revature.nabnak.member.Member;
import com.revature.nabnak.util.HibernateUtil;
import com.revature.nabnak.util.interfaces.Crudable;
import com.revature.nabnak.util.exceptions.InvalidUserInputException;
import com.revature.nabnak.util.exceptions.ResourcePersistanceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CardDao implements Crudable<Card> {

    @Override
    public Card create(Card newCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newCard);
            transaction.commit();
            return newCard;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Card> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Card> cards = session.createQuery("FROM Card").list();
            transaction.commit();
            return cards;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public Card findById(String id) {
        try {
            int intId = Integer.parseInt(id);
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Card card = session.get(Card.class, intId);
            transaction.commit();
            return card;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(Card updatedCard) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedCard);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Card card = session.load(Card.class, id);
            session.remove(card);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public List<Card> findAllByUser(String email) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Card where member_email= :email");
            query.setParameter("email", email);
            List<Card> cards = query.getResultList();
            transaction.commit();
            return cards;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
