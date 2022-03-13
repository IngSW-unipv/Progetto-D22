package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno le query a database secondo il pattern Dao
 * @author ArrayIndexOutOfBoundsException
 */
public interface IVoucherDao {
    /**
     * Metodo da implementare per poter ottenere tutte le tuple sottoforma di classi di modello
     * @return una lista di VoucherEntity
     */
    public List<VoucherEntity> findAll();
    /**
     * Metodo da implementare per poter ottenere la VoucherEntity il cui id è uguale alla stringa passata come parametro
     * @param id
     * @return una VoucherEntity
     */
    public VoucherEntity findById(String id);
    /**
     * Metodo da implementare per poter salvare la VoucherEntity passata come parametro nel database
     * @param voucher
     */
    public void persist(VoucherEntity voucher);
    /**
     * Metodo da implementare per poter aggiornare le informazioni della VoucherEntity passata come parametro nel database
     * @param voucher
     */
    public void update(VoucherEntity voucher);
    /**
     * Metodo da implementare per poter eliminare la VoucherEntity passata come parametro nel database
     * @param voucher
     */
    public void delete(VoucherEntity voucher);

}
