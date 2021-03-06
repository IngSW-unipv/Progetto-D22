package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IDatiPersonaliDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     *
     * @return una lista di DatiPersonaliEntity
     */
    List<DatiPersonaliEntity> findAll();

    /**
     * Metodo da implementare per poter ottenere i dati personali dell'account il cui username è uguale alla stringa passata come parametro
     *
     * @param user
     * @return una DatiPersonaliEntity
     */
    DatiPersonaliEntity findByUsername(String user);

    /**
     * Metodo da implementare per poter salvare la DatiPeronaliEntity passata come parametro nel database
     *
     * @param datiPersonali
     */
    void persist(DatiPersonaliEntity datiPersonali);

    /**
     * Metodo da implementare per poter aggiornare le informazioni di DatiPersonaliEntity passata come parametro nel database
     *
     * @param datiPersonali
     */
    void update(DatiPersonaliEntity datiPersonali);

    /**
     * Metodo da implementare per poter eliminare DatiPersonaliEntity passata come parametro nel database
     *
     * @param datiPersonali
     */
    void delete(DatiPersonaliEntity datiPersonali);

}
