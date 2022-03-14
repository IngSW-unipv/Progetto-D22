package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IViaggiPreferitiService {

    List<ViaggiPreferitiEntity> findAll();

    ViaggiPreferitiEntity findById(String id);

    List<ViaggiPreferitiEntity> findByUsername(String user);

    void persist(ViaggiPreferitiEntity viaggiPreferiti);

    void update(ViaggiPreferitiEntity viaggiPreferiti);

    void deleteById(String id);

}
