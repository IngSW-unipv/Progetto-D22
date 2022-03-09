package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IVoucherDao;

import java.util.List;

public class VoucherDao implements IVoucherDao {

    private HibernateConnection conn;

    public VoucherDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<VoucherEntity> findAll(){
        List<VoucherEntity> voucherEntities = (List<VoucherEntity>) conn.getCurrentSession().createQuery("from VoucherEntity ").list();
        return voucherEntities;
    }


    public VoucherEntity findById(String id) {
        VoucherEntity voucherEntity = (VoucherEntity) conn.getCurrentSession().get(VoucherEntity.class, id);
        return voucherEntity;
    }

    public void persist(VoucherEntity voucher) {
        conn.getCurrentSession().save(voucher);
    }


    public void update(VoucherEntity voucher) {
        conn.getCurrentSession().update(voucher);
    }

    public void delete(VoucherEntity voucher) {
        conn.getCurrentSession().delete(voucher);
    }

}
