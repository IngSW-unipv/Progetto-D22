package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IRimborsoStrategy {

    /**
     * Metodo da implementare per poter ottenere il voucher con un valore calcolato in base al rimborso che viene passato come parametro.
     * @param r (Rimborso)
     */
    VoucherEntity getPrezzoTot(Rimborso r);

}
