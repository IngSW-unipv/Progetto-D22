package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StopsDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStopsService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di StopsDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class StopsService implements IStopsService {

    private static StopsDao stopsDao;

    public StopsService() {
        stopsDao = new StopsDao();
    }

    @Override
    public List<StopsEntity> findAll() {
        stopsDao.getConn().openCurrentSession();
        List<StopsEntity> stops = stopsDao.findAll();
        stopsDao.getConn().closeCurrentSession();
        return stops;
    }

    public StopsDao getStopsDao() {
        return stopsDao;
    }

}
