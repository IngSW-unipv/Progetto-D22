package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAccountDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 * @author ArrayIndexOutOfBoundsException
 */
public class AccountDao implements IAccountDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;
    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public AccountDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }
    /**
     * Metodo che implementa la query al database che ritorna tutte le tuple nella table di riferimento
     * @return una lista di AccountEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<AccountEntity> findAll(){
        List<AccountEntity> accountEntities = (List<AccountEntity>) conn.getCurrentSession().createQuery("from AccountEntity ").list();
        return accountEntities;
    }
    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di username pari alla stringa passata come parametro
     * @param user stringa che identifica l'username dell'account che vogliamo trovare in database
     * @return una AccountEntity
     */
    @Override
    public AccountEntity findByUsername(String user) {
        AccountEntity accountEntity = (AccountEntity) conn.getCurrentSession().get(AccountEntity.class, user);
        return accountEntity;
    }
    /**
     * Metodo che salva in database l'AccountEntity passata come parametro
     * @param account
     */
    @Override
    public void persist(AccountEntity account) {
        conn.getCurrentSession().save(account);
    }
    /**
     * Metodo che salva le modifiche in database dell'AccountEntity passata come parametro
     * @param account
     */
    @Override
    public void update(AccountEntity account) {
        conn.getCurrentSession().update(account);
    }
    /**
     * Metodo che elimina da database l'AccounEntity passata come parametro
     * @param account
     */
    @Override
    public void delete(AccountEntity account) {
        conn.getCurrentSession().delete(account);
    }
}
