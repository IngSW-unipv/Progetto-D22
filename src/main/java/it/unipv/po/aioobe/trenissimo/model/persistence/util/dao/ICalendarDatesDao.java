package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface ICalendarDatesDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di CalendarDatesEntity
     */
    public List<CalendarDatesEntity> findAll();

}
