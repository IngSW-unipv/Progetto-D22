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
        datiPersonaliDao.openCurrentSession();
        List<DatiPersonaliEntity> datiPersonali = datiPersonaliDao.findAll();
        datiPersonaliDao.closeCurrentSession();
        return datiPersonali;
    }

    public DatiPersonaliEntity findById(String id) {
        datiPersonaliDao.openCurrentSession();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findById(id);
        datiPersonaliDao.closeCurrentSession();
        return datiPersonali;
    }

    public void persist(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.openCurrentSessionwithTransaction();
        datiPersonaliDao.persist(datiPersonali);
        datiPersonaliDao.closeCurrentSessionwithTransaction();
    }

    public void update(DatiPersonaliEntity datiPersonali) {
        datiPersonaliDao.openCurrentSessionwithTransaction();
        datiPersonaliDao.update(datiPersonali);
        datiPersonaliDao.closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        datiPersonaliDao.openCurrentSessionwithTransaction();
        DatiPersonaliEntity datiPersonali = datiPersonaliDao.findById(id);
        datiPersonaliDao.delete(datiPersonali);
        datiPersonaliDao.closeCurrentSessionwithTransaction();
    }

    public DatiPersonaliDao datiPersonaliDao() {
        return datiPersonaliDao;
    }
}
