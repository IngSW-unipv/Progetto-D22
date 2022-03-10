package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;

import java.util.Locale;

public class RimborsoStrategy implements IRimborsoStrategy {

    public final double RIMBORSOPIU3GIORNI = (double)75/100;
    public final double RIMBORSO1GIORNO = (double)50/100;

    @Override
    public VoucherEntity getPrezzoTot(Rimborso r) {
        if((r.getTitoloViaggioEntity().getDataPartenza().toLocalDate().toEpochDay() - r.getDataRichiesta().toEpochDay()) >= 3 ) {
            VoucherEntity v = new VoucherEntity();
            v.setVoucherId();
            v.setPrezzo(Double.valueOf(String.format(Locale.US,"%.2f", r.getStoricoAcquisti().getPrezzo()*RIMBORSOPIU3GIORNI)));
            return v;
        }
        else if((r.getTitoloViaggioEntity().getDataPartenza().toLocalDate().toEpochDay() - r.getDataRichiesta().toEpochDay()) == 1) {
            VoucherEntity v = new VoucherEntity();
            v.setVoucherId();
            v.setPrezzo(Double.valueOf(String.format(Locale.US,"%.2f", r.getStoricoAcquisti().getPrezzo()*RIMBORSO1GIORNO)));
            return v;
        }
        else
            return null;

    }
}
