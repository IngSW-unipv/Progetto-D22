package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IStoricoAcquistiDao;

import java.util.List;

public class StoricoAcquistiDao extends HibernateConnection implements IStoricoAcquistiDao {

    public StoricoAcquistiDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<StoricoAcquistiEntity> findAll() {
        List<StoricoAcquistiEntity> storicoAcquistiEntities = (List<StoricoAcquistiEntity>) getCurrentSession().createQuery("from StoricoAcquistiEntity ").list();
        return storicoAcquistiEntities;
    }

}
