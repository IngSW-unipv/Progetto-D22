package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;

public interface IRimborsoStrategy {

    VoucherEntity getPrezzoTot(Rimborso r);

}
