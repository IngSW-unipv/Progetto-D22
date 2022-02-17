package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IDatiPersonaliDao;
import java.util.List;

public class DatiPersonaliDao extends HibernateConnection implements IDatiPersonaliDao {

    @SuppressWarnings("unchecked")
    public List<DatiPersonaliEntity> findAll() {
        List<DatiPersonaliEntity> datiPersonaliEntities = (List<DatiPersonaliEntity>) getCurrentSession().createQuery("from DatiPersonaliEntity ").list();
        return datiPersonaliEntities;
    }


    public DatiPersonaliEntity findById(String id) {
        DatiPersonaliEntity datiPersonaliEntity = (DatiPersonaliEntity) getCurrentSession().get(DatiPersonaliEntity.class, Integer.parseInt(id));
        return datiPersonaliEntity;
    }


    public void persist(DatiPersonaliEntity datiPersonali) {
        getCurrentSession().save(datiPersonali);
    }


    public void update(DatiPersonaliEntity datiPersonali) {
        getCurrentSession().update(datiPersonali);
    }


    public void delete(DatiPersonaliEntity datiPersonali) {
        getCurrentSession().delete(datiPersonali);
    }
}
