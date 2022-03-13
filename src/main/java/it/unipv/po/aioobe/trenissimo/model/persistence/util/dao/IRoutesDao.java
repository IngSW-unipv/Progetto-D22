package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IRoutesDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di RoutesEntity
     */
    public List<RoutesEntity> findAll();

}
