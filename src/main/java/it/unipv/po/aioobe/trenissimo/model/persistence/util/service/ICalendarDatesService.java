package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarDatesEntity;

import java.util.List;

public interface ICalendarDatesService {

    public List<CalendarDatesEntity> findAll();

}
