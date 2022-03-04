package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;

import java.util.List;

public interface IDatiPersonaliService {

    public List<DatiPersonaliEntity> findAll();
    public DatiPersonaliEntity findByUsername(String user);
    public void persist(DatiPersonaliEntity datiPersonali);
    public void update(DatiPersonaliEntity datiPersonali);
    public void deleteByUsername(String user);

}
