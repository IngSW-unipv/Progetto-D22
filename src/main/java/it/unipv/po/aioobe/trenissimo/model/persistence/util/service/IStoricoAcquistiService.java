package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

public interface IStoricoAcquistiService {

    public List<StoricoAcquistiEntity> findAll();
    public StoricoAcquistiEntity findById(String id);
    public List<StoricoAcquistiEntity> findByUsername(String username);
    public StoricoAcquistiEntity findByTitoloViaggioId(String id);
    public void persist(StoricoAcquistiEntity storicoAcquisti);
    public void update(StoricoAcquistiEntity storicoAcquisti);
    public void deleteById(String id);
}
