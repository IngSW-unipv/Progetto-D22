package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IDatiPersonaliDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class DatiPersonaliDao implements IDatiPersonaliDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;

    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public DatiPersonaliDao() {
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
     *
     * @return una lista di DatiPersonaliEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<DatiPersonaliEntity> findAll() {
        List<DatiPersonaliEntity> datiPersonaliEntities = (List<DatiPersonaliEntity>) conn.getCurrentSession().createQuery("from DatiPersonaliEntity ").list();
        return datiPersonaliEntities;
    }

    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di username pari alla stringa passata come parametro
     *
     * @param user stringa che identifica l'username dell'account di cui vogliamo trovare i dati personali in database
     * @return un DatiPersonaliEntity
     */
    @Override
    public DatiPersonaliEntity findByUsername(String user) {
        DatiPersonaliEntity datiPersonaliEntity = conn.getCurrentSession().get(DatiPersonaliEntity.class, user);
        return datiPersonaliEntity;
    }

    /**
     * Metodo che salva in database DatiPersonaliEntity passata come parametro
     *
     * @param datiPersonali
     */
    @Override
    public void persist(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().save(datiPersonali);
    }

    /**
     * Metodo che salva le modifiche in database di DatiPersonaliEntity passata come parametro
     *
     * @param datiPersonali
     */
    @Override
    public void update(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().update(datiPersonali);
    }

    /**
     * Metodo che elimina da database DatiPersonaliEntity passata come parametro
     *
     * @param datiPersonali
     */
    @Override
    public void delete(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().delete(datiPersonali);
    }
}
