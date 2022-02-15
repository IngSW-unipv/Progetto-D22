package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.TripsEntity;

import java.util.List;

public interface ITripsDao {

    public List<TripsEntity> findAll();

}
