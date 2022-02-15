package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.CalendarEntity;
import java.util.List;

public interface ICalendarService {

    public List<CalendarEntity> findAll();

}
