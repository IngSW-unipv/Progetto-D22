package it.unipv.po.aioobe.trenissimo.model.persistence.service;


import it.unipv.po.aioobe.trenissimo.model.persistence.dao.AgencyDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AgencyEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IAgencyService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di AgencyDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class AgencyService implements IAgencyService {

    private static AgencyDao agencyDao;

    public AgencyService() {
        agencyDao = new AgencyDao();
    }

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