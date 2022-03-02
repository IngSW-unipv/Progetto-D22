package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import java.util.List;

public interface IStopTimesDao {

    public List<StopTimesEntity> findAll();

}
