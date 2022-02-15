package it.unipv.trenissimo.model.persistence;


import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public class AgencyService {

    private static AgencyDao agencyDao;

    public AgencyService() {
        agencyDao = new AgencyDao();
    }

    /*public AgencyEntity findById(String id) {
        agencyDao.openCurrentSession();
        AgencyEntity book = agencyDao.findById(id);
        agencyDao.closeCurrentSession();
        return book;
    }*/

    public List<AgencyEntity> findAll() {
        agencyDao.openCurrentSession();
        List<AgencyEntity> agencies = agencyDao.findAll();
        agencyDao.closeCurrentSession();
        return agencies;
    }

    public AgencyDao agencyDao() {
        return agencyDao;
    }
}