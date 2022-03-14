package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IStoricoAcquistiService {

    List<StoricoAcquistiEntity> findAll();

    StoricoAcquistiEntity findById(String id);

    List<StoricoAcquistiEntity> findByUsername(String username);

    StoricoAcquistiEntity findByTitoloViaggioId(String id);

    void persist(StoricoAcquistiEntity storicoAcquisti);

    void update(StoricoAcquistiEntity storicoAcquisti);

    void deleteById(String id);
}
