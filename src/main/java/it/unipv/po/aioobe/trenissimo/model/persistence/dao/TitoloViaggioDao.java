package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ITitoloViaggioDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class TitoloViaggioDao implements ITitoloViaggioDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;

    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public TitoloViaggioDao() {
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
     * @return una lista di TitoloViaggioEntity
     */
    @SuppressWarnings("unchecked")
    public List<TitoloViaggioEntity> findAll() {
        List<TitoloViaggioEntity> titoloViaggioEntities = (List<TitoloViaggioEntity>) conn.getCurrentSession().createQuery("from TitoloViaggioEntity ").list();
        return titoloViaggioEntities;
    }

    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di id pari alla stringa passata come parametro
     *
     * @param id stringa che identifica l'id del titolo di viaggio che vogliamo trovare in database
     * @return una TitoloViaggioEntity
     */
    public TitoloViaggioEntity findById(String id) {
        TitoloViaggioEntity titoloViaggioEntity = conn.getCurrentSession().get(TitoloViaggioEntity.class, id);
        return titoloViaggioEntity;
    }

    /**
     * Metodo che salva in database la TitoloViaggioEntity passata come parametro
     *
     * @param titoloViaggio
     */
    public void persist(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().save(titoloViaggio);
    }

    /**
     * Metodo che salva le modifiche in database della TitoloViaggioEntity passata come parametro
     *
     * @param titoloViaggio
     */
    public void update(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().update(titoloViaggio);
    }

    /**
     * Metodo che elimina da database la TitoloViaggioEntity passata come parametro
     *
     * @param titoloViaggio
     */
    public void delete(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().delete(titoloViaggio);
    }
}
