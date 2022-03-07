package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAccountDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ITitoloViaggioDao;

import java.util.List;

/**
 * @
 */
public class TitoloViaggioDao implements ITitoloViaggioDao {

    private HibernateConnection conn;

    public TitoloViaggioDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<TitoloViaggioEntity> findAll(){
        List<TitoloViaggioEntity> titoloViaggioEntities = (List<TitoloViaggioEntity>) conn.getCurrentSession().createQuery("from TitoloViaggioEntity ").list();
        return titoloViaggioEntities;
    }


    public TitoloViaggioEntity findById(String id) {
        TitoloViaggioEntity titoloViaggioEntity = (TitoloViaggioEntity) conn.getCurrentSession().get(TitoloViaggioEntity.class, id);
        return titoloViaggioEntity;
    }

    public void persist(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().save(titoloViaggio);
    }


    public void update(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().update(titoloViaggio);
    }

    public void delete(TitoloViaggioEntity titoloViaggio) {
        conn.getCurrentSession().delete(titoloViaggio);
    }
}
