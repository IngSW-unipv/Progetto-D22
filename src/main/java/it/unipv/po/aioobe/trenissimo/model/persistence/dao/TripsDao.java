package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ITripsDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 * @author ArrayIndexOutOfBoundsException
 */
public class TripsDao implements ITripsDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;
    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public TripsDao() {
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
     * @return una lista di TripsEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<TripsEntity> findAll() {
        List<TripsEntity> tripsEntities = (List<TripsEntity>) conn.getCurrentSession().createQuery("from TripsEntity ").list();
        return tripsEntities;
    }

}
