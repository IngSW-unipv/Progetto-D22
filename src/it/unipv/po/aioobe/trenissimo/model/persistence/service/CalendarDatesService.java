package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.CalendarDatesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICalendarDatesService;

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
