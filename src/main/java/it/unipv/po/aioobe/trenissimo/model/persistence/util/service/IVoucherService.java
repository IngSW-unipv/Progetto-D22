package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 * @author ArrayIndexOutOfBoundsException
 */
public interface IVoucherService {

    public List<VoucherEntity> findAll();
    public VoucherEntity findById(String id);
    public void persist(VoucherEntity voucher);
    public void update(VoucherEntity voucher);
    public void deleteById(String id);

}
