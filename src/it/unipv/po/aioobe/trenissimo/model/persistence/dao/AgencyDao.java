package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAgencyDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public class AgencyDao extends HibernateConnection implements IAgencyDao  {

    public AgencyDao() {
        super();
    }

    /*public AgencyEntity findById(String id) {
        AgencyEntity agency = (AgencyEntity) getCurrentSession().get(AgencyEntity.class, id);
        return agency;
    }*/

    @SuppressWarnings("unchecked")
    public List<AgencyEntity> findAll() {
        List<AgencyEntity> agencyEntities = (List<AgencyEntity>) getCurrentSession().createQuery("from AgencyEntity ").list();
        return agencyEntities;
    }


}

