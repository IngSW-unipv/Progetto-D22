package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;

import java.sql.SQLException;
import java.util.List;

public interface ITitoloVIaggioService {

    public List<TitoloViaggioEntity> findAll();
    public TitoloViaggioEntity findById(String id);
    public void persist(TitoloViaggioEntity titoloViaggio);
    public void update(TitoloViaggioEntity titoloViaggio);
    public void deleteById(String id);
}
