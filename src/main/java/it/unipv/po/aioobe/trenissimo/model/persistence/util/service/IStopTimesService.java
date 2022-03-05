package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;

import java.util.List;

public interface IStopTimesService {

    public List<StopTimesEntity> findAll();

}
