package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IViaggiPreferitiDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ViaggiPreferitiDao implements IViaggiPreferitiDao {

    private HibernateConnection conn;

    public ViaggiPreferitiDao() {
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
    public List<ViaggiPreferitiEntity> findAll() {
        List<ViaggiPreferitiEntity> viaggiPreferitiEntities = (List<ViaggiPreferitiEntity>) conn.getCurrentSession().createQuery("from ViaggiPreferitiEntity ").list();
        return viaggiPreferitiEntities;
    }

    @Override
    public ViaggiPreferitiEntity findById(String id) {
        ViaggiPreferitiEntity viaggiPreferitiEntity = (ViaggiPreferitiEntity) conn.getCurrentSession().get(ViaggiPreferitiEntity.class, Integer.parseInt(id));
        return viaggiPreferitiEntity;
    }

    @Override
    public List<ViaggiPreferitiEntity> findByUsername(String user) {
        Criteria criteria = conn.getCurrentSession().createCriteria(ViaggiPreferitiEntity.class);
        criteria.add(Restrictions.eq("username", user));
        List<ViaggiPreferitiEntity> viaggi = (List<ViaggiPreferitiEntity>) criteria.list();
        return viaggi;
    }

    @Override
    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().save(viaggiPreferiti);
    }

    @Override
    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().update(viaggiPreferiti);
    }

    @Override
    public void delete(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().delete(viaggiPreferiti);
    }
}
