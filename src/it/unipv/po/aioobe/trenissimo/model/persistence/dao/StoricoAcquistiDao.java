package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStoricoAcquistiDao;

import java.util.List;

public class StoricoAcquistiDao implements IStoricoAcquistiDao {

    private HibernateConnection conn;

    public StoricoAcquistiDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<StoricoAcquistiEntity> findAll() {
        List<StoricoAcquistiEntity> storicoAcquistiEntities = (List<StoricoAcquistiEntity>) conn.getCurrentSession().createQuery("from StoricoAcquistiEntity ").list();
        return storicoAcquistiEntities;
    }

    public StoricoAcquistiEntity findById(String id) {
        StoricoAcquistiEntity storicoAcquistiEntity = (StoricoAcquistiEntity) conn.getCurrentSession().get(StoricoAcquistiEntity.class, Integer.parseInt(id));
        return storicoAcquistiEntity;
    }

    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().save(storicoAcquisti);
    }

    public void update(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().update(storicoAcquisti);
    }

    public void delete(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().delete(storicoAcquisti);
    }

}
