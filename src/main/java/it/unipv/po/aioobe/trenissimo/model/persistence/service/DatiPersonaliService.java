package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.DatiPersonaliDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IDatiPersonaliService;
import java.util.List;

public class DatiPersonaliService implements IDatiPersonaliService {

    private static DatiPersonaliDao datiPersonaliDao;

    public DatiPersonaliService() {
        datiPersonaliDao = new DatiPersonaliDao();
    }

    public List<DatiPersonaliEntity> findAll() {
        datiPersonaliDao.getConn().openCurrentSession();
        List<DatiPersonaliEntity> datiPersonali = datiPersonaliDao.findAll();
        datiPersonaliDao.getConn().closeCurrentSession();
        return datiPersonali;
    }

    public DatiPersonaliEntity findById(String id) {
        datiPersonaliDao.getConn().openCurrentSession();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findById(id);
        datiPersonaliDao.getConn().closeCurrentSession();
        return datiPersonali;
    }

    public void persist(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        datiPersonaliDao.persist(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        datiPersonaliDao.update(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        datiPersonaliDao.getConn().openCurrentSessionwithTransaction();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findById(id);
        datiPersonaliDao.delete(datiPersonali);
        datiPersonaliDao.getConn().closeCurrentSessionwithTransaction();
    }

    public DatiPersonaliDao getDatiPersonaliDao() {
        return datiPersonaliDao;
    }
}
