package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IRoutesDao;

import java.util.List;

public class RoutesDao extends HibernateConnection implements IRoutesDao {

    public RoutesDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<RoutesEntity> findAll() {
        List<RoutesEntity> routesEntities = (List<RoutesEntity>) getCurrentSession().createQuery("from RoutesEntity").list();
        return routesEntities;
    }


}
