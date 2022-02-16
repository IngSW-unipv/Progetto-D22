package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.StopTimesEntity;
import java.util.List;

public interface IStopTimesDao {

    public List<StopTimesEntity> findAll();

}
