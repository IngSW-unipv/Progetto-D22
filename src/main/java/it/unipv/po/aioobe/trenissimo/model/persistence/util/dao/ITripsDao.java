package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface ITripsDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     *
     * @return una lista di TripsEntity
     */
    List<TripsEntity> findAll();

}
