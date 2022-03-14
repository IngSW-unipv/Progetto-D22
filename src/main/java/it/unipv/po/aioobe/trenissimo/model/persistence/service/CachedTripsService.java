package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ITripsService;

import java.util.List;

/**
 * Versione Singleton della classe TripsService
 */
public class CachedTripsService implements ITripsService, ICached {
    private static CachedTripsService instance = null;
    private static TripsService tripsService;
    /**
     * Lista che conterrà tutti i risultati che verranno trovati da database
     */
    private List<TripsEntity> cachedFindAll = null;

    private CachedTripsService() {
        tripsService = new TripsService();
    }

    public static CachedTripsService getInstance() {
        if (instance == null) {
            instance = new CachedTripsService();
        }
        return instance;
    }

    @Override
    public List<TripsEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = tripsService.findAll();
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