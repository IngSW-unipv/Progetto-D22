package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IViaggiPreferitiDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;
/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 * @author ArrayIndexOutOfBoundsException
 */
public class ViaggiPreferitiDao implements IViaggiPreferitiDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;
    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public ViaggiPreferitiDao() {
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
     * @return una lista di ViaggiPreferitiEntity
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<ViaggiPreferitiEntity> findAll() {
        List<ViaggiPreferitiEntity> viaggiPreferitiEntities = (List<ViaggiPreferitiEntity>) conn.getCurrentSession().createQuery("from ViaggiPreferitiEntity ").list();
        return viaggiPreferitiEntities;
    }
    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di id pari alla stringa passata come parametro
     * @param id stringa che identifica l'id del viaggio preferito che vogliamo trovare in database
     * @return una ViaggiPreferitiEntity
     */
    @Override
    public ViaggiPreferitiEntity findById(String id) {
        ViaggiPreferitiEntity viaggiPreferitiEntity = (ViaggiPreferitiEntity) conn.getCurrentSession().get(ViaggiPreferitiEntity.class, Integer.parseInt(id));
        return viaggiPreferitiEntity;
    }
    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di username pari alla stringa passata come parametro
     * @param user stringa che identifica l'username dell'account di cui vogliamo trovare i viaggi preferiti
     * @return una lista di ViaggiPreferitiEntity
     */
    @Override
    public List<ViaggiPreferitiEntity> findByUsername(String user) {
        Criteria criteria = conn.getCurrentSession().createCriteria(ViaggiPreferitiEntity.class);
        criteria.add(Restrictions.eq("username", user));
        List<ViaggiPreferitiEntity> viaggi = (List<ViaggiPreferitiEntity>) criteria.list();
        return viaggi;
    }
    /**
     * Metodo che salva in database la ViaggiPreferitiEntity passata come parametro
     * @param viaggiPreferiti
     */
    @Override
    public void persist(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().save(viaggiPreferiti);
    }
    /**
     * Metodo che salva le modifiche in database della ViaggiPreferitiEntity passata come parametro
     * @param viaggiPreferiti
     */
    @Override
    public void update(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().update(viaggiPreferiti);
    }
    /**
     * Metodo che elimina da database la ViaggiPreferitiEntity passata come parametro
     * @param viaggiPreferiti
     */
    @Override
    public void delete(ViaggiPreferitiEntity viaggiPreferiti) {
        conn.getCurrentSession().delete(viaggiPreferiti);
    }
}
