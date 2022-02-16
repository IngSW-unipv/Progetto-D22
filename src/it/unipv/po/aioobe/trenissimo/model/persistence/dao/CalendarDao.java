package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICalendarDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarEntity;

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
