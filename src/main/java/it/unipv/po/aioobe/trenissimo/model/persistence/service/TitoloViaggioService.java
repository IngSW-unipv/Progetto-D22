package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.TitoloViaggioDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.ITitoloVIaggioService;

import java.util.List;

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
    //public void persist(AccountEntity account) throws SQLException {
    public void persist(TitoloViaggioEntity titoloViaggio) {

        //try {
        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        titoloViaggioDao.persist(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();
             /*catch (Exception e) {
                System.out.println(e.getCause().getCause().getLocalizedMessage());
            }*/

    }


    @Override
    public void update(TitoloViaggioEntity titoloViaggio) {

        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        titoloViaggioDao.update(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();

    }

    @Override
    public void deleteByUsername(String id){

        titoloViaggioDao.getConn().openCurrentSessionwithTransaction();
        TitoloViaggioEntity titoloViaggio = titoloViaggioDao.findById(id);
        titoloViaggioDao.delete(titoloViaggio);
        titoloViaggioDao.getConn().closeCurrentSessionwithTransaction();

    }


    public TitoloViaggioDao getTitoloViaggioDao() {
        return titoloViaggioDao;
    }
}
