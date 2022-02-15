package it.unipv.trenissimo.model.persistence.dao;

import it.unipv.trenissimo.model.persistence.HibernateConnection;
import it.unipv.trenissimo.model.persistence.entity.TripsEntity;
import it.unipv.trenissimo.model.persistence.util.ITripsDao;

import java.util.List;

public class TripsDao extends HibernateConnection implements ITripsDao {

    public TripsDao() {
        super();
    }

    @SuppressWarnings("unchecked")
    public List<TripsEntity> findAll() {
        List<TripsEntity> tripsEntities = (List<TripsEntity>) getCurrentSession().createQuery("from TripsEntity ").list();
        return tripsEntities;
    }

}
