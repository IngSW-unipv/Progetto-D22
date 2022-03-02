package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ICalendarDatesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

public class CalendarDatesDao implements ICalendarDatesDao {

    private HibernateConnection conn;

    public CalendarDatesDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<CalendarDatesEntity> findAll() {
        List<CalendarDatesEntity> calendarDatesEntities = (List<CalendarDatesEntity>) conn.getCurrentSession().createQuery("from CalendarDatesEntity").list();
        return calendarDatesEntities;
    }
}
