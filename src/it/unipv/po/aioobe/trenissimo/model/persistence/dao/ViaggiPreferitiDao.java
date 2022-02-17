package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IViaggiPreferitiDao;
import java.util.List;

public class ViaggiPreferitiDao extends HibernateConnection implements IViaggiPreferitiDao {

    @SuppressWarnings("unchecked")
    public List<ViaggiPreferitiEntity> findAll() {
        List<ViaggiPreferitiEntity> viaggiPreferitiEntities = (List<ViaggiPreferitiEntity>) getCurrentSession().createQuery("from ViaggiPreferitiEntity ").list();
        return viaggiPreferitiEntities;
    }

    public ViaggiPreferitiEntity findById(String id) {
        ViaggiPreferitiEntity viaggiPreferitiEntity = (ViaggiPreferitiEntity) getCurrentSession().get(ViaggiPreferitiEntity.class, Integer.parseInt(id));
        return viaggiPreferitiEntity;
    }

    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        getCurrentSession().save(viaggiPreferiti);
    }

    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        getCurrentSession().update(viaggiPreferiti);
    }

    public void delete(ViaggiPreferitiEntity viaggiPreferiti) {
        getCurrentSession().delete(viaggiPreferiti);
    }
}
