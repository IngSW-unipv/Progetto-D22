package it.unipv.trenissimo.model.persistence.service;

import it.unipv.trenissimo.model.persistence.dao.RoutesDao;
import it.unipv.trenissimo.model.persistence.dao.StopsDao;
import it.unipv.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.trenissimo.model.persistence.util.IStopsService;

import java.util.List;

public class StopsService implements IStopsService {

    private static StopsDao routesDao;

    public StopsService() {
        routesDao = new StopsDao();
    }

    public List<StopsEntity> findAll() {
        routesDao.openCurrentSession();
        List<StopsEntity> stops = routesDao.findAll();
        routesDao.closeCurrentSession();
        return stops;
    }

    public StopsDao calendarDatesDao() {
        return routesDao;
    }

}
