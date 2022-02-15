package it.unipv.trenissimo.model.persistence;

import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public interface IAgencyDao {

    public List<AgencyEntity> findAll();
    //public AgencyEntity findById(String id);

}
