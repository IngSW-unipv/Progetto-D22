package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.RoutesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IRoutesService;

import java.util.List;

public class RoutesService implements IRoutesService {

    private static RoutesDao routesDao;

    public RoutesService() {
        routesDao = new RoutesDao();
    }

    public List<RoutesEntity> findAll() {
        routesDao.getConn().openCurrentSession();
        List<RoutesEntity> routes = routesDao.findAll();
        routesDao.getConn().closeCurrentSession();
        return routes;
    }

    public RoutesDao calendarDatesDao() {
        return routesDao;
    }

}
