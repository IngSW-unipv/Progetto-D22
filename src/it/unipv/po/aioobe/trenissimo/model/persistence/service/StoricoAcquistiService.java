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
        storicoAcquistiDao.openCurrentSession();
        List<StoricoAcquistiEntity> storicoAcquisti = storicoAcquistiDao.findAll();
        storicoAcquistiDao.closeCurrentSession();
        return storicoAcquisti;
    }

    public StoricoAcquistiEntity findById(String id) {
        storicoAcquistiDao.openCurrentSession();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.closeCurrentSession();
        return storicoAcquisti;
    }

    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.openCurrentSessionwithTransaction();
        storicoAcquistiDao.persist(storicoAcquisti);
        storicoAcquistiDao.closeCurrentSessionwithTransaction();
    }

    public void update(StoricoAcquistiEntity storicoAcquisti) {
        storicoAcquistiDao.openCurrentSessionwithTransaction();
        storicoAcquistiDao.update(storicoAcquisti);
        storicoAcquistiDao.closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        storicoAcquistiDao.openCurrentSessionwithTransaction();
        StoricoAcquistiEntity storicoAcquisti = storicoAcquistiDao.findById(id);
        storicoAcquistiDao.delete(storicoAcquisti);
        storicoAcquistiDao.closeCurrentSessionwithTransaction();
    }

    public StoricoAcquistiDao storicoAcquistiDao() {
        return storicoAcquistiDao;
    }

}
