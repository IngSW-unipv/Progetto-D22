package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StopTimesDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IRoutesService;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStopTimesService;

import java.util.List;

public class CachedStopTimesService implements IStopTimesService, ICached {
    private static CachedStopTimesService instance = null;
    private static StopTimesService stopTimesService;

    private CachedStopTimesService() {
        stopTimesService = new StopTimesService();
    }

    public static CachedStopTimesService getInstance() {
        if (instance == null) {
            instance = new CachedStopTimesService();
        }
        return instance;
    }

    private List<StopTimesEntity> cachedFindAll = null;

    public List<StopTimesEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = stopTimesService.findAll();
        }
        return cachedFindAll;
    }

    @Override
    public void clearCache() {
        cachedFindAll = null;
    }
}