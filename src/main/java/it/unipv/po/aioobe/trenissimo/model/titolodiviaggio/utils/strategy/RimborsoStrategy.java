package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.Voucher;

import java.util.Locale;

public class RimborsoStrategy implements IRimborsoStrategy {

    public final double RIMBORSOPIU3GIORNI = (double)75/100;
    public final double RIMBORSO1GIORNO = (double)50/100;

    @Override
    public Voucher getPrezzoTot(Rimborso r) {
        if((r.getBiglietto().getViaggio().getDataPartenza().toEpochDay() - r.getDataRichiesta().toEpochDay()) >= 3 ) {
            Voucher v = new Voucher(Double.valueOf(String.format(Locale.US,"%.2f", r.getBiglietto().getPrezzo()*RIMBORSOPIU3GIORNI)));
            return v;
        }
        else if((r.getBiglietto().getViaggio().getDataPartenza().toEpochDay() - r.getDataRichiesta().toEpochDay()) == 1) {
            Voucher v = new Voucher(Double.valueOf(String.format(Locale.US,"%.2f", r.getBiglietto().getPrezzo()*RIMBORSO1GIORNO)));
            return v;
        }
        else
            return null;
    }
}
