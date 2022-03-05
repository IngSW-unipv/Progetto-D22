package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public interface IAgencyService {

    public List<AgencyEntity> findAll();

}
