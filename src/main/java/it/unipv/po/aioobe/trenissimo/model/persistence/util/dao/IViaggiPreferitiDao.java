package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;
/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IViaggiPreferitiDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di ViaggiPreferitiEntity
     */
    public List<ViaggiPreferitiEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere la ViaggiPreferitiEntity il cui id è uguale alla stringa passata come parametro
     * @param id
     * @return una ViaggiPreferitiEntity
     */
    public ViaggiPreferitiEntity findById(String id);
    /**
     * Metodo da implementare per poter ottenere la lista di viaggi preferiti dell'account il cui username è uguale alla stringa passata come parametro
     * @param user
     * @return una lista di ViaggiPreferitiEntity
     */
    public List<ViaggiPreferitiEntity> findByUsername(String user);
    /**
     * Metodo da implementare per poter salvare la ViaggiPreferitiEntity passata come parametro nel database
     * @param viaggiPreferiti
     */
    public void persist(ViaggiPreferitiEntity viaggiPreferiti);
    /**
     * Metodo da implementare per poter aggiornare le informazioni della ViaggiPreferitiEntity passata come parametro nel database
     * @param viaggiPreferiti
     */
    public void update(ViaggiPreferitiEntity viaggiPreferiti);
    /**
     * Metodo da implementare per poter eliminare la ViaggiPreferitiEntity passata come parametro nel database
     * @param viaggiPreferiti
     */
    public void delete(ViaggiPreferitiEntity viaggiPreferiti);

}
