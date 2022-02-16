package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.StoricoAcquistiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
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

    public StoricoAcquistiDao storicoAcquistiDao() {
        return storicoAcquistiDao;
    }

}
