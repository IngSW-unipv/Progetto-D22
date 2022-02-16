package it.unipv.trenissimo.model.persistence.service;

import it.unipv.trenissimo.model.persistence.dao.CalendarDao;
import it.unipv.trenissimo.model.persistence.entity.CalendarEntity;
import it.unipv.trenissimo.model.persistence.util.ICalendarService;

import java.util.List;

public class CalendarService implements ICalendarService {

    private static CalendarDao calendarDao;

    public CalendarService() {
        calendarDao = new CalendarDao();
    }

    public List<CalendarEntity> findAll() {
        calendarDao.openCurrentSession();
        List<CalendarEntity> calendars = calendarDao.findAll();
        calendarDao.closeCurrentSession();
        return calendars;
    }

    public CalendarDao calendarDatesDao() {
        return calendarDao;
    }

}
