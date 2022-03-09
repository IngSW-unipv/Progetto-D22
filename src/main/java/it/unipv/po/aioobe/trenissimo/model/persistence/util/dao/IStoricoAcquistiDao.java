package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

public interface IStoricoAcquistiDao {

    public List<StoricoAcquistiEntity> findAll();
    public StoricoAcquistiEntity findById(String id);
    public List<StoricoAcquistiEntity> findByUsername(String user);
    public StoricoAcquistiEntity findByTitoloViaggioId(String id);
    public void persist(StoricoAcquistiEntity storicoAcquisti);
    public void update(StoricoAcquistiEntity storicoAcquisti);
    public void delete(StoricoAcquistiEntity storicoAcquisti);
}
