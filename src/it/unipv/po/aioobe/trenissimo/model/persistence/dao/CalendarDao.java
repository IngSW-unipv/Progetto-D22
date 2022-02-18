package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ICalendarDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarEntity;

import java.util.List;

public class CalendarDao implements ICalendarDao {

    private HibernateConnection conn;

    public CalendarDao() {
        this.conn = new HibernateConnection();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<CalendarEntity> findAll() {
        List<CalendarEntity> calendarEntities = (List<CalendarEntity>) conn.getCurrentSession().createQuery("from CalendarEntity").list();
        return calendarEntities;
    }
}
