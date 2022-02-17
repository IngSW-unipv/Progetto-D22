package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStoricoAcquistiDao;

import java.util.List;

public class StoricoAcquistiDao extends HibernateConnection implements IStoricoAcquistiDao {

    public StoricoAcquistiDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<StoricoAcquistiEntity> findAll() {
        List<StoricoAcquistiEntity> storicoAcquistiEntities = (List<StoricoAcquistiEntity>) getCurrentSession().createQuery("from StoricoAcquistiEntity ").list();
        return storicoAcquistiEntities;
    }

    public StoricoAcquistiEntity findById(String id) {
        StoricoAcquistiEntity storicoAcquistiEntity = (StoricoAcquistiEntity) getCurrentSession().get(StoricoAcquistiEntity.class, Integer.parseInt(id));
        return storicoAcquistiEntity;
    }

    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        getCurrentSession().save(storicoAcquisti);
    }

    public void update(StoricoAcquistiEntity storicoAcquisti) {
        getCurrentSession().update(storicoAcquisti);
    }

    public void delete(StoricoAcquistiEntity storicoAcquisti) {
        getCurrentSession().delete(storicoAcquisti);
    }

}
