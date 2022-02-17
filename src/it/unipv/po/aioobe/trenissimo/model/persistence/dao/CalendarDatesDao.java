package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ICalendarDatesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;

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
