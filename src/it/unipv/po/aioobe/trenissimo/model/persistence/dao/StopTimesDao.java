package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStopTimesDao;

import java.util.List;

public class StopTimesDao extends HibernateConnection implements IStopTimesDao {

    public StopTimesDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<StopTimesEntity> findAll() {
        List<StopTimesEntity> stopTimesEntities = (List<StopTimesEntity>) getCurrentSession().createQuery("from StopTimesEntity ").list();
        return stopTimesEntities;
    }

}
