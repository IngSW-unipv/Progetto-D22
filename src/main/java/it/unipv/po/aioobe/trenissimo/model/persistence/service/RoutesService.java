package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.RoutesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IRoutesService;

import java.util.List;

public class RoutesService implements IRoutesService {

    private static RoutesDao routesDao;

    public RoutesService() {
        routesDao = new RoutesDao();
    }

    @Override
    public List<RoutesEntity> findAll() {
        routesDao.getConn().openCurrentSession();
        List<RoutesEntity> routes = routesDao.findAll();
        routesDao.getConn().closeCurrentSession();
        return routes;
    }

    public RoutesDao routesDao() {
        return routesDao;
    }

}
