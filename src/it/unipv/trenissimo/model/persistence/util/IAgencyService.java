package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public interface IAgencyService {

    public List<AgencyEntity> findAll();
    //public AgencyEntity findById(String id);

}