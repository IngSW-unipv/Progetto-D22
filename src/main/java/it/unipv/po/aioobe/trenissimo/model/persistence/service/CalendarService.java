package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.CalendarDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ICalendarService;

import java.util.List;

public class CalendarService implements ICalendarService {

    private static CalendarDao calendarDao;

    public CalendarService() {
        calendarDao = new CalendarDao();
    }

    @Override
    public List<CalendarEntity> findAll() {
        calendarDao.getConn().openCurrentSession();
        List<CalendarEntity> calendars = calendarDao.findAll();
        calendarDao.getConn().closeCurrentSession();
        return calendars;
    }

    public CalendarDao getCalendarDatesDao() {
        return calendarDao;
    }

}
