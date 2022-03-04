package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStoricoAcquistiDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
        StoricoAcquistiEntity storicoAcquistiEntity = (StoricoAcquistiEntity) conn.getCurrentSession().get(StoricoAcquistiEntity.class, id);
        return storicoAcquistiEntity;
    }

    //Questo metodo ritorna un solo account dato che l'username è univoco
    public List<StoricoAcquistiEntity> findByUsername(String user) {
        Criteria criteria = conn.getCurrentSession().createCriteria(StoricoAcquistiEntity.class);
        criteria.add(Restrictions.eq("username", user));
        List<StoricoAcquistiEntity> storicoAcquisti = (List<StoricoAcquistiEntity>) criteria.list();
        return storicoAcquisti;
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
