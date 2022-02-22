package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.TripsDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ITripsService;

import java.util.List;

public class CachedTripsService implements ITripsService, ICached {
    private static CachedTripsService instance = null;
    private static TripsService tripsService;

    private CachedTripsService() {
        tripsService = new TripsService();
    }

    public static CachedTripsService getInstance() {
        if (instance == null) {
            instance = new CachedTripsService();
        }
        return instance;
    }

    private List<TripsEntity> cachedFindAll = null;

    public List<TripsEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = tripsService.findAll();
        }
        return cachedFindAll;
    }

    @Override
    public void clearCache() {
        cachedFindAll = null;
    }
}