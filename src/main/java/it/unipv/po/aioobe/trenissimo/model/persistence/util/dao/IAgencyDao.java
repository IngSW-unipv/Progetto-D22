package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IAgencyDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di AgencyEntity
     */
    public List<AgencyEntity> findAll();

}
