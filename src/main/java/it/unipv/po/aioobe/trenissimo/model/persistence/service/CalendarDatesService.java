package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.CalendarDatesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ICalendarDatesService;

import java.util.List;

public class CalendarDatesService implements ICalendarDatesService {
    private static CalendarDatesDao calendarDatesDao;

    public CalendarDatesService() {
        calendarDatesDao = new CalendarDatesDao();
    }

    public List<CalendarDatesEntity> findAll() {
        calendarDatesDao.getConn().openCurrentSession();
        List<CalendarDatesEntity> calendarDates = calendarDatesDao.findAll();
        calendarDatesDao.getConn().closeCurrentSession();
        return calendarDates;
    }

    public CalendarDatesDao getCalendarDatesDao() {
        return calendarDatesDao;
    }
}
