package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;

import java.util.List;

public interface ITripsDao {

    public List<TripsEntity> findAll();

}
