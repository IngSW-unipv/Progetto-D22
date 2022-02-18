package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAgencyDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public class AgencyDao implements IAgencyDao  {

    private HibernateConnection conn;

    public AgencyDao() {
        this.conn = new HibernateConnection();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    /*public AgencyEntity findById(String id) {
        AgencyEntity agency = (AgencyEntity) getCurrentSession().get(AgencyEntity.class, id);
        return agency;
    }*/

    @SuppressWarnings("unchecked")
    public List<AgencyEntity> findAll() {
        List<AgencyEntity> agencyEntities = (List<AgencyEntity>) conn.getCurrentSession().createQuery("from AgencyEntity ").list();
        return agencyEntities;
    }


}

