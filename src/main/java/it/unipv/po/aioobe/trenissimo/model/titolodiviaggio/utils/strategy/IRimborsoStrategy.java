package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.Voucher;

public interface IRimborsoStrategy {

    Voucher getPrezzoTot(Rimborso r);

}
