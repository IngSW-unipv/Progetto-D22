package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;

import java.util.List;

public interface IStopsDao {

    public List<StopsEntity> findAll();

}
