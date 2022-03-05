package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IDatiPersonaliDao;

import java.util.List;

public class DatiPersonaliDao implements IDatiPersonaliDao {

    private HibernateConnection conn;

    public DatiPersonaliDao() {
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
    public List<DatiPersonaliEntity> findAll() {
        List<DatiPersonaliEntity> datiPersonaliEntities = (List<DatiPersonaliEntity>) conn.getCurrentSession().createQuery("from DatiPersonaliEntity ").list();
        return datiPersonaliEntities;
    }

    @Override
    public DatiPersonaliEntity findByUsername(String user) {
        DatiPersonaliEntity datiPersonaliEntity = (DatiPersonaliEntity) conn.getCurrentSession().get(DatiPersonaliEntity.class, user);
        return datiPersonaliEntity;
    }

    @Override
    public void persist(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().save(datiPersonali);
    }

    @Override
    public void update(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().update(datiPersonali);
    }

    @Override
    public void delete(DatiPersonaliEntity datiPersonali) {
        conn.getCurrentSession().delete(datiPersonali);
    }
}
