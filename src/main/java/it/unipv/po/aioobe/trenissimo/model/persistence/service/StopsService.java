package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StopsDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStopsService;

import java.util.List;

public class StopsService implements IStopsService {

    private static StopsDao stopsDao;

    public StopsService() {
        stopsDao = new StopsDao();
    }

    public List<StopsEntity> findAll() {
        stopsDao.getConn().openCurrentSession();
        List<StopsEntity> stops = stopsDao.findAll();
        stopsDao.getConn().closeCurrentSession();
        return stops;
    }

    public StopsDao getStopsDao() {
        return stopsDao;
    }

}
