package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ITripsService;

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

    @Override
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