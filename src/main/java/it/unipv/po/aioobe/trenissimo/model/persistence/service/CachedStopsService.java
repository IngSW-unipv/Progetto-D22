package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStopsService;

import java.util.List;
/**
 * Versione Singleton della classe StopsService
 */
public class CachedStopsService implements IStopsService, ICached {
    private static CachedStopsService instance = null;
    private static StopsService stopsService;
    /**
     * Lista che conterrà tutti i risultati che verranno trovati da database
     */
    private List<StopsEntity> cachedFindAll = null;

    private CachedStopsService() {
        stopsService = new StopsService();
    }

    public static CachedStopsService getInstance() {
        if (instance == null) {
            instance = new CachedStopsService();
        }
        return instance;
    }

    @Override
    public List<StopsEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = stopsService.findAll();
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
