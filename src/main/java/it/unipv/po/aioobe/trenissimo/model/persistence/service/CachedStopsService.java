package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStopsService;

import java.util.List;

public class CachedStopsService implements IStopsService, ICached {
    private static CachedStopsService instance = null;
    private static StopsService stopsService;

    private CachedStopsService() {
        stopsService = new StopsService();
    }

    public static CachedStopsService getInstance() {
        if (instance == null) {
            instance = new CachedStopsService();
        }
        return instance;
    }

    private List<StopsEntity> cachedFindAll = null;

    public List<StopsEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = stopsService.findAll();
        }
        return cachedFindAll;
    }

    @Override
    public void clearCache() {
        cachedFindAll = null;
    }
}
