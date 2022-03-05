package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.DatiPersonaliDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IDatiPersonaliService;
import java.util.List;

public class DatiPersonaliService implements IDatiPersonaliService {

    private static DatiPersonaliDao datiPersonaliDao;

    public DatiPersonaliService() {
        datiPersonaliDao = new DatiPersonaliDao();
    }

    @Override
    public List<DatiPersonaliEntity> findAll() {
        datiPersonaliDao.getConn().openCurrentSession();
        List<DatiPersonaliEntity> datiPersonali = datiPersonaliDao.findAll();
        datiPersonaliDao.getConn().closeCurrentSession();
        return datiPersonali;
    }

    @Override
    public DatiPersonaliEntity findByUsername(String user) {
        datiPersonaliDao.getConn().openCurrentSession();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findByUsername(user);
        datiPersonaliDao.getConn().closeCurrentSession();
        return datiPersonali;
    }

    @Override
    public void persist(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        datiPersonaliDao.persist(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        datiPersonaliDao.update(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteByUsername(String user) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findByUsername(user);
        datiPersonaliDao.delete(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    public DatiPersonaliDao getDatiPersonaliDao() {
        return datiPersonaliDao;
    }
}
