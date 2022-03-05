package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.ITripsDao;

import java.util.List;

public class TripsDao implements ITripsDao {

    private HibernateConnection conn;

    public TripsDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<TripsEntity> findAll() {
        List<TripsEntity> tripsEntities = (List<TripsEntity>) conn.getCurrentSession().createQuery("from TripsEntity ").list();
        return tripsEntities;
    }

}
