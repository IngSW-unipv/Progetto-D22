package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StoricoAcquistiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IStoricoAcquistiService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di StoricoAcquistiDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 * @author ArrayIndexOutOfBoundsException
 */
public class StoricoAcquistiService implements IStoricoAcquistiService {

    private static StoricoAcquistiDao storicoAcquistiDao;

    public StoricoAcquistiService() {
        storicoAcquistiDao = new StoricoAcquistiDao();
    }

    @Override
    public List<StoricoAcquistiEntity> findAll() {
        storicoAcquistiDao.getConn().openCurrentSession();
        List<StoricoAcquistiEntity> storicoAcquisti = storicoAcquistiDao.findAll();
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    @Override
    public StoricoAcquistiEntity findById(String id) {
        storicoAcquistiDao.getConn().openCurrentSession();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    @Override
    public List<StoricoAcquistiEntity> findByUsername(String username) {
        storicoAcquistiDao.getConn().openCurrentSession();
        List<StoricoAcquistiEntity> storicoAcquisti = (List<StoricoAcquistiEntity>) storicoAcquistiDao.findByUsername(username);
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    @Override
    public StoricoAcquistiEntity findByTitoloViaggioId(String id) {
        storicoAcquistiDao.getConn().openCurrentSession();
        StoricoAcquistiEntity storicoAcquisti = (StoricoAcquistiEntity) storicoAcquistiDao.findByTitoloViaggioId(id);
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    @Override
    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        storicoAcquistiDao.persist(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        storicoAcquistiDao.update(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteById(String id) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.delete(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public StoricoAcquistiDao getStoricoAcquistiDao() {
        return storicoAcquistiDao;
    }

}
