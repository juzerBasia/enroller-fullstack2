package com.company.enroller.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseConnector {

    protected static DatabaseConnector instance = null;

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    private Session session;

    private DatabaseConnector() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void teardown() {
        session.close();
        HibernateUtil.shutdown();
        instance = null;
    }

    public <T> void update(T t) {

        Transaction transaction = instance.getSession().beginTransaction();
        instance.getSession().update(t);
        transaction.commit();

    }

    public Session getSession() {
        return session;
    }

}
