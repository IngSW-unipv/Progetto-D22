package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.ViaggiPreferitiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IViaggiPreferitiService;
import java.util.List;

public class ViaggiPreferitiService implements IViaggiPreferitiService {

    private static ViaggiPreferitiDao viaggiPreferitiDao;

    public ViaggiPreferitiService() {
        viaggiPreferitiDao = new ViaggiPreferitiDao();
    }

    public List<ViaggiPreferitiEntity> findAll() {
        viaggiPreferitiDao.openCurrentSession();
        List<ViaggiPreferitiEntity> viaggiPreferiti = viaggiPreferitiDao.findAll();
        viaggiPreferitiDao.closeCurrentSession();
        return viaggiPreferiti;
    }

    public ViaggiPreferitiEntity findById(String id) {
        viaggiPreferitiDao.openCurrentSession();
        ViaggiPreferitiEntity viaggiPreferiti = viaggiPreferitiDao.findById(id);
        viaggiPreferitiDao.closeCurrentSession();
        return viaggiPreferiti;
    }

    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.openCurrentSessionwithTransaction();
        viaggiPreferitiDao.persist(viaggiPreferiti);
        viaggiPreferitiDao.closeCurrentSessionwithTransaction();
    }

    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.openCurrentSessionwithTransaction();
        viaggiPreferitiDao.update(viaggiPreferiti);
        viaggiPreferitiDao.closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        viaggiPreferitiDao.openCurrentSessionwithTransaction();
        ViaggiPreferitiEntity viaggiPreferiti = viaggiPreferitiDao.findById(id);
        viaggiPreferitiDao.delete(viaggiPreferiti);
        viaggiPreferitiDao.closeCurrentSessionwithTransaction();
    }

    public ViaggiPreferitiDao viaggiPreferitiDao() {
        return viaggiPreferitiDao;
    }
}
