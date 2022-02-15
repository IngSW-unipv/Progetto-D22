package it.unipv.trenissimo.model.persistence.dao;

import it.unipv.trenissimo.model.persistence.HibernateConnection;
import it.unipv.trenissimo.model.persistence.util.ICalendarDao;
import it.unipv.trenissimo.model.persistence.entity.CalendarEntity;

import java.util.List;

public class CalendarDao extends HibernateConnection implements ICalendarDao {

    public CalendarDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<CalendarEntity> findAll() {
        List<CalendarEntity> calendarEntities = (List<CalendarEntity>) getCurrentSession().createQuery("from CalendarEntity").list();
        return calendarEntities;
    }
}
