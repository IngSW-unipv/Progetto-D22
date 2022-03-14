package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IStoricoAcquistiDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class StoricoAcquistiDao implements IStoricoAcquistiDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;

    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public StoricoAcquistiDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    /**
     * Metodo che implementa la query al database che ritorna tutte le tuple nella table di riferimento
     *
     * @return una lista di StoricoAcquistiEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<StoricoAcquistiEntity> findAll() {
        List<StoricoAcquistiEntity> storicoAcquistiEntities = (List<StoricoAcquistiEntity>) conn.getCurrentSession().createQuery("from StoricoAcquistiEntity ").list();
        return storicoAcquistiEntities;
    }

    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di id pari alla stringa passata come parametro
     *
     * @param id stringa che identifica l'id dell'acquisto che vogliamo trovare in database
     * @return una StoricoAcquistoEntity
     */
    @Override
    public StoricoAcquistiEntity findById(String id) {
        StoricoAcquistiEntity storicoAcquistiEntity = conn.getCurrentSession().get(StoricoAcquistiEntity.class, id);
        return storicoAcquistiEntity;
    }

    /**
     * Metodo che implementa la query al database che ritorna le tupla che hanno il valore di username pari alla stringa passata come parametro
     *
     * @param user stringa che identifica l'username dell'account di cui vogliamo trovare gli acquisto in database
     * @return una lista di StoricoAcquistoEntity
     */
    @Override
    public List<StoricoAcquistiEntity> findByUsername(String user) {
        Criteria criteria = conn.getCurrentSession().createCriteria(StoricoAcquistiEntity.class);
        criteria.add(Restrictions.eq("username", user));
        List<StoricoAcquistiEntity> storicoAcquisti = (List<StoricoAcquistiEntity>) criteria.list();
        return storicoAcquisti;
    }

    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di titolo_viaggio_id pari alla stringa passata come parametro
     *
     * @param id stringa che identifica l'id del titolo di viaggio che vogliamo trovare in database
     * @return una StoricoAcquistoEntity
     */
    @Override
    public StoricoAcquistiEntity findByTitoloViaggioId(String id) {
        Criteria criteria = conn.getCurrentSession().createCriteria(StoricoAcquistiEntity.class);
        criteria.add(Restrictions.eq("titoloViaggioId", id));
        StoricoAcquistiEntity storicoAcquisti = (StoricoAcquistiEntity) criteria.uniqueResult();
        return storicoAcquisti;
    }

    /**
     * Metodo che salva in database la StoricoAcquistiEntity passata come parametro
     *
     * @param storicoAcquisti
     */
    @Override
    public void persist(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().save(storicoAcquisti);
    }

    /**
     * Metodo che salva le modifiche in database della StoricoAcquistiEntity passata come parametro
     *
     * @param storicoAcquisti
     */
    @Override
    public void update(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().update(storicoAcquisti);
    }

    /**
     * Metodo che elimina da database la StoricoAcquistiEntity passata come parametro
     *
     * @param storicoAcquisti
     */
    @Override
    public void delete(StoricoAcquistiEntity storicoAcquisti) {
        conn.getCurrentSession().delete(storicoAcquisti);
    }

}
