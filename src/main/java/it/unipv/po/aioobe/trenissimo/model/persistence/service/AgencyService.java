package it.unipv.po.aioobe.trenissimo.model.persistence.service;


import it.unipv.po.aioobe.trenissimo.model.persistence.dao.AgencyDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IAgencyService;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public class AgencyService implements IAgencyService {

    private static AgencyDao agencyDao;

    public AgencyService() {
        agencyDao = new AgencyDao();
    }

    /*public AgencyEntity findById(String id) {
        agencyDao.openCurrentSession();
        AgencyEntity agency = agencyDao.findById(id);
        agencyDao.closeCurrentSession();
        return agency;
    }*/

    @Override
    public List<AgencyEntity> findAll() {
        agencyDao.getConn().openCurrentSession();
        List<AgencyEntity> agencies = agencyDao.findAll();
        agencyDao.getConn().closeCurrentSession();
        return agencies;
    }

    public AgencyDao getAgencyDao() {
        return agencyDao;
    }
}