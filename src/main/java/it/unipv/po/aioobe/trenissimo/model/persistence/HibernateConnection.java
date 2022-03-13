package it.unipv.po.aioobe.trenissimo.model.persistence;

import it.unipv.po.aioobe.trenissimo.model.persistence.util.exception.ConnectionDBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Classe mediante la quale definiamo una connessione al database con il framework Hibernate
 * See <a href="https://docs.jboss.org/hibernate/orm/5.2/javadocs/"></a>
 */
public class HibernateConnection {

    private Session currentSession;
    private Transaction currentTransaction;

    private static HibernateConnection instance = null;

    private HibernateConnection() {}

    public static HibernateConnection getInstance() {
        if (instance == null) {
            instance = new HibernateConnection();
        }
        return instance;
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() throws ConnectionDBException {

        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
            sessionFactory = configuration.buildSessionFactory(builder.build());
            return sessionFactory;

        } catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());
            throw new ConnectionDBException(e.getLocalizedMessage());
        }
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

}
