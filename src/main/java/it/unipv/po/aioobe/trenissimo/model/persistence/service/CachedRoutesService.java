package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IRoutesService;

import java.util.List;

/**
 * Versione Singleton della classe RoutesService
 */
public class CachedRoutesService implements IRoutesService, ICached {
    private static CachedRoutesService instance = null;
    private static RoutesService routesService;
    /**
     * Lista che conterrà tutti i risultati che verranno trovati da database
     */
    private List<RoutesEntity> cachedFindAll = null;

    private CachedRoutesService() {
        routesService = new RoutesService();
    }

    public static CachedRoutesService getInstance() {
        if (instance == null) {
            instance = new CachedRoutesService();
        }
        return instance;
    }

    @Override
    public List<RoutesEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = routesService.findAll();
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
