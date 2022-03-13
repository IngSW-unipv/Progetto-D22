package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IVoucherDao;

import java.util.List;

/**
 * Classe che, secondo il pattern DAO, implementa le query a database sottoforma di metodi
 * @author ArrayIndexOutOfBoundsException
 */
public class VoucherDao implements IVoucherDao {
    /**
     * Connessione al database mediante il framework di Hibernate
     */
    private HibernateConnection conn;
    /**
     * Viene assegnata all'attributo privato conn l'istanza Singleton della connessione di Hibernate al database
     */
    public VoucherDao() {
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
     * @return una lista di VoucherEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<VoucherEntity> findAll(){
        List<VoucherEntity> voucherEntities = (List<VoucherEntity>) conn.getCurrentSession().createQuery("from VoucherEntity ").list();
        return voucherEntities;
    }
    /**
     * Metodo che implementa la query al database che ritorna la tupla che ha il valore di username pari alla stringa passata come parametro
     * @param id stringa che identifica l'id del voucher che vogliamo trovare in database
     * @return una VoucherEntity
     */
    @Override
    public VoucherEntity findById(String id) {
        VoucherEntity voucherEntity = (VoucherEntity) conn.getCurrentSession().get(VoucherEntity.class, id);
        return voucherEntity;
    }
    /**
     * Metodo che salva in database la VoucherEntity passata come parametro
     * @param voucher
     */
    @Override
    public void persist(VoucherEntity voucher) {
        conn.getCurrentSession().save(voucher);
    }
    /**
     * Metodo che salva le modifiche in database della VoucherEntity passata come parametro
     * @param voucher
     */
    @Override
    public void update(VoucherEntity voucher) {
        conn.getCurrentSession().update(voucher);
    }
    /**
     * Metodo che elimina da database la VoucherEntity passata come parametro
     * @param voucher
     */
    @Override
    public void delete(VoucherEntity voucher) {
        conn.getCurrentSession().delete(voucher);
    }

}
