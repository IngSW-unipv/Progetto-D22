package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IStopTimesDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     *
     * @return una lista di StopTimesEntity
     */
    List<StopTimesEntity> findAll();

}
