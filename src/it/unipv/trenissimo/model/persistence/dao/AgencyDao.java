package it.unipv.trenissimo.model.persistence.dao;

import it.unipv.trenissimo.model.persistence.HibernateConnection;
import it.unipv.trenissimo.model.persistence.util.IAgencyDao;
import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

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

