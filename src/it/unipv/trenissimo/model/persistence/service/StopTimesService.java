package it.unipv.trenissimo.model.persistence.service;

import it.unipv.trenissimo.model.persistence.dao.StopTimesDao;
import it.unipv.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.trenissimo.model.persistence.util.IStopTimesService;

import java.util.List;

public class StopTimesService implements IStopTimesService {

    private static StopTimesDao stopTimesDao;

    public StopTimesService() {
        stopTimesDao = new StopTimesDao();
    }

    public List<StopTimesEntity> findAll() {
        stopTimesDao.openCurrentSession();
        List<StopTimesEntity> stopTimes = stopTimesDao.findAll();
        stopTimesDao.closeCurrentSession();
        return stopTimes;
    }

    public StopTimesDao stopTimesDao() {
        return stopTimesDao;
    }

}
