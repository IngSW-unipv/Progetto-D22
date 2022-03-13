package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 * @author ArrayIndexOutOfBoundsException
 */
public interface IAccountDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di AccountEntity
     */
    public List<AccountEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere l'account il cui username è uguale alla stringa passata come parametro
     * @param user
     * @return una AccountEntity
     */
    public AccountEntity findByUsername(String user);
    /**
     * Metodo da implementare per poter salvare l'AccountEntity passata come parametro nel database
     * @param account
     */
    public void persist(AccountEntity account);
    /**
     * Metodo da implementare per poter aggiornare le informazioni dell'AccountEntity passata come parametro nel database
     * @param account
     */
    public void update(AccountEntity account);
    /**
     * Metodo da implementare per poter eliminare l'AccountEntity passata come parametro nel database
     * @param account
     */
    public void delete(AccountEntity account);

}
