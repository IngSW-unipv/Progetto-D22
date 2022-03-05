package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StopTimesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStopTimesService;

import java.util.List;

public class StopTimesService implements IStopTimesService {

    private static StopTimesDao stopTimesDao;

    public StopTimesService() {
        stopTimesDao = new StopTimesDao();
    }

    @Override
    public List<StopTimesEntity> findAll() {
        stopTimesDao.getConn().openCurrentSession();
        List<StopTimesEntity> stopTimes = stopTimesDao.findAll();
        stopTimesDao.getConn().closeCurrentSession();
        return stopTimes;
    }

    public StopTimesDao getStopTimesDao() {
        return stopTimesDao;
    }

}
