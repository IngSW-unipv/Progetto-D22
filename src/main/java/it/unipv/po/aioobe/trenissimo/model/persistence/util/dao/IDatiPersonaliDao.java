package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;

import java.util.List;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IDatiPersonaliDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di DatiPersonaliEntity
     */
    public List<DatiPersonaliEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere i dati personali dell'account il cui username è uguale alla stringa passata come parametro
     * @param user
     * @return una DatiPersonaliEntity
     */
    public DatiPersonaliEntity findByUsername(String user);
    /**
     * Metodo da implementare per poter salvare la DatiPeronaliEntity passata come parametro nel database
     * @param datiPersonali
     */
    public void persist(DatiPersonaliEntity datiPersonali);
    /**
     * Metodo da implementare per poter aggiornare le informazioni di DatiPersonaliEntity passata come parametro nel database
     * @param datiPersonali
     */
    public void update(DatiPersonaliEntity datiPersonali);
    /**
     * Metodo da implementare per poter eliminare DatiPersonaliEntity passata come parametro nel database
     * @param datiPersonali
     */
    public void delete(DatiPersonaliEntity datiPersonali);

}
