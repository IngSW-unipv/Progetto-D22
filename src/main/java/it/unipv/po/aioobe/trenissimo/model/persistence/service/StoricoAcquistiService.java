package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StoricoAcquistiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStoricoAcquistiService;

import java.util.List;

public class StoricoAcquistiService implements IStoricoAcquistiService {

    private static StoricoAcquistiDao storicoAcquistiDao;

    public StoricoAcquistiService() {
        storicoAcquistiDao = new StoricoAcquistiDao();
    }

    public List<StoricoAcquistiEntity> findAll() {
        storicoAcquistiDao.getConn().openCurrentSession();
        List<StoricoAcquistiEntity> storicoAcquisti = storicoAcquistiDao.findAll();
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    public StoricoAcquistiEntity findById(String id) {
        storicoAcquistiDao.getConn().openCurrentSession();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.getConn().closeCurrentSession();
        return storicoAcquisti;
    }

    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        storicoAcquistiDao.persist(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        storicoAcquistiDao.update(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        storicoAcquistiDao.getConn().openCurrentSessionwithTransaction();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.delete(storicoAcquisti);
        storicoAcquistiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public StoricoAcquistiDao storicoAcquistiDao() {
        return storicoAcquistiDao;
    }

}
