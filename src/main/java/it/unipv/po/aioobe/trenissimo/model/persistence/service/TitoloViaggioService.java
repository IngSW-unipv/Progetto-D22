package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.TitoloViaggioDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ITitoloVIaggioService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di TitoloViaggioDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class TitoloViaggioService implements ITitoloVIaggioService {

    private static TitoloViaggioDao titoloViaggioDao;

    public TitoloViaggioService() {
        titoloViaggioDao = new TitoloViaggioDao();
    }

    @Override
    public List<TitoloViaggioEntity> findAll() {
        titoloViaggioDao.getConn().openCurrentSession();
        List<TitoloViaggioEntity> titoliViaggio = titoloViaggioDao.findAll();
        titoloViaggioDao.getConn().closeCurrentSession();
        return titoliViaggio;
    }

    @Override
    public TitoloViaggioEntity findById(String id) {
        titoloViaggioDao.getConn().openCurrentSession();
        TitoloViaggioEntity titoloViaggio = titoloViaggioDao.findById(id);
        titoloViaggioDao.getConn().closeCurrentSession();
        return titoloViaggio;
    }

    @Override
    public void persist(TitoloViaggioEntity titoloViaggio) {
        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        titoloViaggioDao.persist(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();
    }


    @Override
    public void update(TitoloViaggioEntity titoloViaggio) {

        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        titoloViaggioDao.update(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();

    }

    @Override
    public void deleteById(String id) {

        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        TitoloViaggioEntity titoloViaggio = titoloViaggioDao.findById(id);
        titoloViaggioDao.delete(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();

    }


    public TitoloViaggioDao getTitoloViaggioDao() {
        return titoloViaggioDao;
    }
}
