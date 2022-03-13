package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 * @author ArrayIndexOutOfBoundsException
 */
public interface IStoricoAcquistiDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di StoricoAcquistiEntity
     */
    public List<StoricoAcquistiEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere l'acquisto il cui id è uguale alla stringa passata come parametro
     * @param id
     * @return una StoricoAcquistiEntity
     */
    public StoricoAcquistiEntity findById(String id);
    /**
     * Metodo da implementare per poter ottenere l'acquisto attraverso l'username dell'account passato come parametro
     * @param user
     * @return una StoricoAcquistiEntity
     */
    public List<StoricoAcquistiEntity> findByUsername(String user);
    /**
     * Metodo da implementare per poter ottenere l'acquisto attraverso l'id del titolo di viaggio passato come parametro
     * @param id
     * @return una StoricoAcquistiEntity
     */
    public StoricoAcquistiEntity findByTitoloViaggioId(String id);
    /**
     * Metodo da implementare per poter salvare la StoricoAcquistiEntity passata come parametro nel database
     * @param storicoAcquisti
     */
    public void persist(StoricoAcquistiEntity storicoAcquisti);
    /**
     * Metodo da implementare per poter aggiornare le informazioni della StoricoAcquistiEntity passata come parametro nel database
     * @param storicoAcquisti
     */
    public void update(StoricoAcquistiEntity storicoAcquisti);
    /**
     * Metodo da implementare per poter eliminare la StoricoAcquistiEntity passata come parametro nel database
     * @param storicoAcquisti
     */
    public void delete(StoricoAcquistiEntity storicoAcquisti);
}
