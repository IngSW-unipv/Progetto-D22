package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.CalendarDatesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ICalendarDatesService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di CalendarDatesDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class CalendarDatesService implements ICalendarDatesService {
    private static CalendarDatesDao calendarDatesDao;

    public CalendarDatesService() {
        calendarDatesDao = new CalendarDatesDao();
    }

    @Override
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
