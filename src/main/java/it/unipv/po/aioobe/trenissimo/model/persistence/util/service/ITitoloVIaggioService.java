package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 * @author ArrayIndexOutOfBoundsException
 */
public interface ITitoloVIaggioService {

    public List<TitoloViaggioEntity> findAll();
    public TitoloViaggioEntity findById(String id);
    public void persist(TitoloViaggioEntity titoloViaggio);
    public void update(TitoloViaggioEntity titoloViaggio);
    public void deleteById(String id);
}
