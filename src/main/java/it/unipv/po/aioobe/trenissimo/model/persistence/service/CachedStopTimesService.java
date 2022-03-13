package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopTimesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStopTimesService;

import java.util.List;

/**
 * Versione Singleton della classe StopTimesService
 */
public class CachedStopTimesService implements IStopTimesService, ICached {
    private static CachedStopTimesService instance = null;
    private static StopTimesService stopTimesService;
    /**
     * Lista che conterrà tutti i risultati che verranno trovati da database
     */
    private List<StopTimesEntity> cachedFindAll = null;

    private CachedStopTimesService() {
        stopTimesService = new StopTimesService();
    }

    public static CachedStopTimesService getInstance() {
        if (instance == null) {
            instance = new CachedStopTimesService();
        }
        return instance;
    }

    @Override
    public List<StopTimesEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = stopTimesService.findAll();
        }
        return cachedFindAll;
    }
    /**
     * Metodo che pone a null la lista cachedFindAll
     */
    @Override
    public void clearCache() {
        cachedFindAll = null;
    }
}