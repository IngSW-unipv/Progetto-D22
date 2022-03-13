package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 * @author ArrayIndexOutOfBoundsException
 */
public interface IViaggiPreferitiService {

    public List<ViaggiPreferitiEntity> findAll();
    public ViaggiPreferitiEntity findById(String id);
    public List<ViaggiPreferitiEntity> findByUsername(String user);
    public void persist(ViaggiPreferitiEntity viaggiPreferiti);
    public void update(ViaggiPreferitiEntity viaggiPreferiti);
    public void deleteById(String id);

}
