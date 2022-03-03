package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.ViaggiPreferitiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IViaggiPreferitiService;
import java.util.List;

public class ViaggiPreferitiService implements IViaggiPreferitiService {

    private static ViaggiPreferitiDao viaggiPreferitiDao;

    public ViaggiPreferitiService() {
        viaggiPreferitiDao = new ViaggiPreferitiDao();
    }

    public List<ViaggiPreferitiEntity> findAll() {
        viaggiPreferitiDao.getConn().openCurrentSession();
        List<ViaggiPreferitiEntity> viaggiPreferiti = viaggiPreferitiDao.findAll();
        viaggiPreferitiDao.getConn().closeCurrentSession();
        return viaggiPreferiti;
    }

    public ViaggiPreferitiEntity findById(String id) {
        viaggiPreferitiDao.getConn().openCurrentSession();
        ViaggiPreferitiEntity viaggiPreferiti = viaggiPreferitiDao.findById(id);
        viaggiPreferitiDao.getConn().closeCurrentSession();
        return viaggiPreferiti;
    }

    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.getConn().openCurrentSessionwithTransaction();
        viaggiPreferitiDao.persist(viaggiPreferiti);
        viaggiPreferitiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.getConn().openCurrentSessionwithTransaction();
        viaggiPreferitiDao.update(viaggiPreferiti);
        viaggiPreferitiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        viaggiPreferitiDao.getConn().openCurrentSessionwithTransaction();
        ViaggiPreferitiEntity viaggiPreferiti = viaggiPreferitiDao.findById(id);
        viaggiPreferitiDao.delete(viaggiPreferiti);
        viaggiPreferitiDao.getConn().closeCurrentSessionwithTransaction();
    }

    public ViaggiPreferitiDao getViaggiPreferitiDao() {
        return viaggiPreferitiDao;
    }
}
