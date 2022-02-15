package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

public interface ICalendarDatesService {

    public List<CalendarDatesEntity> findAll();

}
