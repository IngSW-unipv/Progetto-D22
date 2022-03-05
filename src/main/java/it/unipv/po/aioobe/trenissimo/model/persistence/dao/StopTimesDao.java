package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStopTimesDao;

import java.util.List;

public class StopTimesDao implements IStopTimesDao {

    private HibernateConnection conn;

    public StopTimesDao() {
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
    public List<StopTimesEntity> findAll() {
        List<StopTimesEntity> stopTimesEntities = (List<StopTimesEntity>) conn.getCurrentSession().createQuery("from StopTimesEntity ").list();
        return stopTimesEntities;
    }

}
