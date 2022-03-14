package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAgencyDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class AgencyDao implements IAgencyDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;

    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public AgencyDao() {
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
     * @return una lista di AgencyEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AgencyEntity> findAll() {
        List<AgencyEntity> agencyEntities = (List<AgencyEntity>) conn.getCurrentSession().createQuery("from AgencyEntity ").list();
        return agencyEntities;
    }


}

