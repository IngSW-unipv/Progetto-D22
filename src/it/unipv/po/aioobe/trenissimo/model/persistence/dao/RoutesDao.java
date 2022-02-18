package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IRoutesDao;

import java.util.List;

public class RoutesDao implements IRoutesDao {

    private HibernateConnection conn;

    public RoutesDao() {
        this.conn = new HibernateConnection();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<RoutesEntity> findAll() {
        List<RoutesEntity> routesEntities = (List<RoutesEntity>) conn.getCurrentSession().createQuery("from RoutesEntity").list();
        return routesEntities;
    }


}
