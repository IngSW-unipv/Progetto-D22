package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;

import java.util.List;

public interface ITitoloViaggioDao {

    public List<TitoloViaggioEntity> findAll();
    public TitoloViaggioEntity findById(String id);
    public void persist(TitoloViaggioEntity titoloViaggio);
    public void update(TitoloViaggioEntity titoloViaggio);
    public void delete(TitoloViaggioEntity titoloViaggio);

}
