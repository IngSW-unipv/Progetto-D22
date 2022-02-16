package it.unipv.trenissimo.model.persistence.service;

import it.unipv.trenissimo.model.persistence.dao.StopTimesDao;
import it.unipv.trenissimo.model.persistence.dao.TripsDao;
import it.unipv.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.trenissimo.model.persistence.util.ITripsService;

import java.util.List;

public class TripsService implements ITripsService {

    private static TripsDao tripsDao;

    public TripsService() {
        tripsDao = new TripsDao();
    }

    public List<TripsEntity> findAll() {
        tripsDao.openCurrentSession();
        List<TripsEntity> trips = tripsDao.findAll();
        tripsDao.closeCurrentSession();
        return trips;
    }

    public TripsDao tripsDao() {
        return tripsDao;
    }

}
