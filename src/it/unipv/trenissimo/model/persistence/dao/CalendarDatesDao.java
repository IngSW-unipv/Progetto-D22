package it.unipv.trenissimo.model.persistence.dao;

import it.unipv.trenissimo.model.persistence.HibernateConnection;
import it.unipv.trenissimo.model.persistence.util.ICalendarDatesDao;
import it.unipv.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

public class CalendarDatesDao extends HibernateConnection implements ICalendarDatesDao {

    public CalendarDatesDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<CalendarDatesEntity> findAll() {
        List<CalendarDatesEntity> calendarDatesEntities = (List<CalendarDatesEntity>) getCurrentSession().createQuery("from CalendarDatesEntity").list();
        return calendarDatesEntities;
    }
}
