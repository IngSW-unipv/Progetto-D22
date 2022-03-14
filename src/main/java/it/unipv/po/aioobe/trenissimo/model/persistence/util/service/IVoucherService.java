package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IVoucherService {

    List<VoucherEntity> findAll();

    VoucherEntity findById(String id);

    void persist(VoucherEntity voucher);

    void update(VoucherEntity voucher);

    void deleteById(String id);

}
