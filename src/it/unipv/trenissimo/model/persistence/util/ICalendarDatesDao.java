package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

public interface ICalendarDatesDao {

    public List<CalendarDatesEntity> findAll();

}
