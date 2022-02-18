package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.TripsDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ITripsService;

import java.util.List;

public class TripsService implements ITripsService {

    private static TripsDao tripsDao;

    public TripsService() {
        tripsDao = new TripsDao();
    }

    public List<TripsEntity> findAll() {
        tripsDao.getConn().openCurrentSession();
        List<TripsEntity> trips = tripsDao.findAll();
        tripsDao.getConn().closeCurrentSession();
        return trips;
    }

    public TripsDao tripsDao() {
        return tripsDao;
    }

}
