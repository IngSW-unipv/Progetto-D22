package it.unipv.trenissimo.model.persistence.service;

import it.unipv.trenissimo.model.persistence.dao.CalendarDatesDao;
import it.unipv.trenissimo.model.persistence.entity.CalendarDatesEntity;
import it.unipv.trenissimo.model.persistence.util.ICalendarDatesService;
import it.unipv.trenissimo.model.persistence.util.ICalendarService;

import java.util.List;

public class CalendarDatesService implements ICalendarDatesService {
    private static CalendarDatesDao calendarDatesDao;

    public CalendarDatesService() {
        calendarDatesDao = new CalendarDatesDao();
    }

    public List<CalendarDatesEntity> findAll() {
        calendarDatesDao.openCurrentSession();
        List<CalendarDatesEntity> calendarDates = calendarDatesDao.findAll();
        calendarDatesDao.closeCurrentSession();
        return calendarDates;
    }

    public CalendarDatesDao calendarDatesDao() {
        return calendarDatesDao;
    }
}
