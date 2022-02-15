package it.unipv.trenissimo.model.persistence.dao;

import it.unipv.trenissimo.model.persistence.util.ICalendarDatesDao;
import it.unipv.trenissimo.model.persistence.entity.CalendarDatesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CalendarDatesDao implements ICalendarDatesDao {

    private Session currentSession;

    private Transaction currentTransaction;

    public CalendarDatesDao() {
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

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
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

    /*public CalendarDatesEntity findById(String id) {
        CalendarDatesEntity calendarDates = (CalendarDatesEntity) getCurrentSession().get(CalendarDatesEntity.class, id);
        return calendarDates;
    }*/

    @SuppressWarnings("unchecked")
    public List<CalendarDatesEntity> findAll() {
        List<CalendarDatesEntity> calendarDatesEntities = (List<CalendarDatesEntity>) getCurrentSession().createQuery("from CalendarDatesEntity").list();
        return calendarDatesEntities;
    }
}
