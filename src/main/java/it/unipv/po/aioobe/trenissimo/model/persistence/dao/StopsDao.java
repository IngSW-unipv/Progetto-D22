package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStopsDao;

import java.util.List;
/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 * @author ArrayIndexOutOfBoundsException
 */
public class StopsDao implements IStopsDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;
    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public StopsDao() {
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
     * @return una lista di StopsEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<StopsEntity> findAll() {
        List<StopsEntity> stopsEntities = (List<StopsEntity>) conn.getCurrentSession().createQuery("from StopsEntity").list();
        return stopsEntities;
    }

}
