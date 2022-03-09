package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.VoucherDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IVoucherService;

import java.util.List;

public class VoucherService implements IVoucherService {

    private static VoucherDao voucherDao;

    public VoucherService() {
        voucherDao = new VoucherDao();
    }

    @Override
    public List<VoucherEntity> findAll() {
        voucherDao.getConn().openCurrentSession();
        List<VoucherEntity> voucher = voucherDao.findAll();
        voucherDao.getConn().closeCurrentSession();
        return voucher;
    }

    @Override
    public VoucherEntity findById(String id) {
        voucherDao.getConn().openCurrentSession();
        VoucherEntity voucher = voucherDao.findById(id);
        voucherDao.getConn().closeCurrentSession();
        return voucher;
    }

    @Override
    //public void persist(AccountEntity account) throws SQLException {
    public void persist(VoucherEntity voucher) {

        //try {
        voucherDao.getConn().openCurrentSessionwithTransaction();
        voucherDao.persist(voucher);
        voucherDao.getConn().closeCurrentSessionwithTransaction();
             /*catch (Exception e) {
                System.out.println(e.getCause().getCause().getLocalizedMessage());
            }*/

    }


    @Override
    public void update(VoucherEntity voucher) {

        voucherDao.getConn().openCurrentSessionwithTransaction();
        voucherDao.update(voucher);
        voucherDao.getConn().closeCurrentSessionwithTransaction();

    }

    @Override
    public void deleteById(String id){

        voucherDao.getConn().openCurrentSessionwithTransaction();
        VoucherEntity voucher = voucherDao.findById(id);
        voucherDao.delete(voucher);
        voucherDao.getConn().closeCurrentSessionwithTransaction();

    }


    public VoucherDao getVoucherDao() {
        return voucherDao;
    }

}
