package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import java.util.List;

public interface IVoucherDao {

    public List<VoucherEntity> findAll();
    public VoucherEntity findById(String id);
    public void persist(VoucherEntity voucher);
    public void update(VoucherEntity voucher);
    public void delete(VoucherEntity voucher);

}
