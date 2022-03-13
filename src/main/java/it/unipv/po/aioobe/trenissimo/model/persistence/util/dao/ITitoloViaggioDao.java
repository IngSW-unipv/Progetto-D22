package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface ITitoloViaggioDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di TitoloViaggioEntity
     */
    public List<TitoloViaggioEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere il titolo di viaggio il cui id è uguale alla stringa passata come parametro
     * @param id
     * @return una TitoloViaggioEntity
     */
    public TitoloViaggioEntity findById(String id);
    /**
     * Metodo da implementare per poter salvare la TitoloViaggioEntity passata come parametro nel database
     * @param titoloViaggio
     */
    public void persist(TitoloViaggioEntity titoloViaggio);
    /**
     * Metodo da implementare per poter aggiornare le informazioni della TitoloViaggioEntity passata come parametro nel database
     * @param titoloViaggio
     */
    public void update(TitoloViaggioEntity titoloViaggio);
    /**
     * Metodo da implementare per poter eliminare la TitoloViaggioEntity passata come parametro nel database
     * @param titoloViaggio
     */
    public void delete(TitoloViaggioEntity titoloViaggio);

}
