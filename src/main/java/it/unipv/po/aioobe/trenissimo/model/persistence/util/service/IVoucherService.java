package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import java.util.List;

public interface IVoucherService {

    public List<VoucherEntity> findAll();
    public VoucherEntity findById(String id);
    public void persist(VoucherEntity voucher);
    public void update(VoucherEntity voucher);
    public void deleteById(String id);

}
