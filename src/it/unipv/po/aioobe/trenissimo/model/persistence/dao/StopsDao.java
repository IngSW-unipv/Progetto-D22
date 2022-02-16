package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStopsDao;

import java.util.List;

public class StopsDao extends HibernateConnection implements IStopsDao {

    public StopsDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<StopsEntity> findAll() {
        List<StopsEntity> stopsEntities = (List<StopsEntity>) getCurrentSession().createQuery("from StopsEntity").list();
        return stopsEntities;
    }

}
