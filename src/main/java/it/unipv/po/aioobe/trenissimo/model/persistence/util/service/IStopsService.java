package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;

import java.util.List;

public interface IStopsService {

    public List<StopsEntity> findAll();

}
