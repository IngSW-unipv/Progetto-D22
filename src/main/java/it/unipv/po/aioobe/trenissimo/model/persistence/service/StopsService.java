package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StopsDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStopsService;

import java.util.List;

public class StopsService implements IStopsService {

    private static StopsDao routesDao;

    public StopsService() {
        routesDao = new StopsDao();
    }

    public List<StopsEntity> findAll() {
        routesDao.getConn().openCurrentSession();
        List<StopsEntity> stops = routesDao.findAll();
        routesDao.getConn().closeCurrentSession();
        return stops;
    }

    public StopsDao calendarDatesDao() {
        return routesDao;
    }

}
