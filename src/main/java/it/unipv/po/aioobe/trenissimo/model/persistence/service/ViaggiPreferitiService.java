package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.ViaggiPreferitiDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IViaggiPreferitiService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di ViaggiPreferitiDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class ViaggiPreferitiService implements IViaggiPreferitiService {

    private static ViaggiPreferitiDao viaggiPreferitiDao;

    public ViaggiPreferitiService() {
        viaggiPreferitiDao = new ViaggiPreferitiDao();
    }

    @Override
    public List<ViaggiPreferitiEntity> findAll() {
        viaggiPreferitiDao.getConn().openCurrentSession();
        List<ViaggiPreferitiEntity> viaggiPreferiti = viaggiPreferitiDao.findAll();
        viaggiPreferitiDao.getConn().closeCurrentSession();
        return viaggiPreferiti;
    }

    @Override
    public ViaggiPreferitiEntity findById(String id) {
        viaggiPreferitiDao.getConn().openCurrentSession();
        ViaggiPreferitiEntity viaggiPreferiti = viaggiPreferitiDao.findById(id);
        viaggiPreferitiDao.getConn().closeCurrentSession();
        return viaggiPreferiti;
    }

    @Override
    public List<ViaggiPreferitiEntity> findByUsername(String user) {
        viaggiPreferitiDao.getConn().openCurrentSession();
        List<ViaggiPreferitiEntity> viaggi = viaggiPreferitiDao.findByUsername(user);
        viaggiPreferitiDao.getConn().closeCurrentSession();
        return viaggi;
    }

    @Override
    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.getConn().openCurrentSessionwithTransaction();
        viaggiPreferitiDao.persist(viaggiPreferiti);
        viaggiPreferitiDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        viaggiPreferitiDao.getConn().openCurrentSessionwithTransaction();
        viaggiPreferitiDao.update(viaggiPreferiti);
        viaggiPreferitiDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
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
