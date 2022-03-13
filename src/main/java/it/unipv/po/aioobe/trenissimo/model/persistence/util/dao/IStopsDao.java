package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IStopsDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di StopsEntity
     */
    public List<StopsEntity> findAll();

}
