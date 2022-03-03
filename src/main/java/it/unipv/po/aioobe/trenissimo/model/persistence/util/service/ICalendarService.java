package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.CalendarEntity;
import java.util.List;

public interface ICalendarService {

    public List<CalendarEntity> findAll();

}
