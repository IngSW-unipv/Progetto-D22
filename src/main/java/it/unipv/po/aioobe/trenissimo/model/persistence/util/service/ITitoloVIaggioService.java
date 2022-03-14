package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface ITitoloVIaggioService {

    List<TitoloViaggioEntity> findAll();

    TitoloViaggioEntity findById(String id);

    void persist(TitoloViaggioEntity titoloViaggio);

    void update(TitoloViaggioEntity titoloViaggio);

    void deleteById(String id);
}
