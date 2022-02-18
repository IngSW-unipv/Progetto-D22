package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStopsDao;

import java.util.List;

public class StopsDao implements IStopsDao {

    private HibernateConnection conn;

    public StopsDao() {
        this.conn = new HibernateConnection();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }
    @SuppressWarnings("unchecked")
    public List<StopsEntity> findAll() {
        List<StopsEntity> stopsEntities = (List<StopsEntity>) conn.getCurrentSession().createQuery("from StopsEntity").list();
        return stopsEntities;
    }

}
