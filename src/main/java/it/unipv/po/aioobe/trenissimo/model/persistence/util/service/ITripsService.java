package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;

import java.util.List;

public interface ITripsService {

    public List<TripsEntity> findAll();

}
