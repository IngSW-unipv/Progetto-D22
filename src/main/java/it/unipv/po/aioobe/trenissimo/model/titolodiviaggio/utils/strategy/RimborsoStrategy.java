package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Classe che implementa una strategia per il calcolo del prezzo totale del voucher derivante da un rimborso passato come parametro.
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class RimborsoStrategy implements IRimborsoStrategy {

    public final double RIMBORSOPIU3GIORNI = (double) 75 / 100;
    public final double RIMBORSO1GIORNO = (double) 50 / 100;

    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata. <br>
     * Metodo che calcola il prezzo totale da assegnare al voucher che creerà.
     * Il calcolo del prezzo avviene secondo la seguente strategia: <br>
     * Se la richiesta è effettuata almeno 3 giorni prima della data di partenza del viaggio, si ha un rimborso del 75%; <br>
     * Se la richiesta è effettuata il giorno prima della data di partenza del viaggio, si ha un rimborso del 50%; <br>
     * Se la richiesta è effettuata il giorno stesso della data di partenza del viaggio, non si può avere rimborso. <br>
     *
     * @return VoucherEntity
     */
    @Override
    public VoucherEntity getPrezzoTot(@NotNull Rimborso r) {
        if ((r.getTitoloViaggioEntity().getDataPartenza().toLocalDate().toEpochDay() - r.getDataRichiesta().toEpochDay()) >= 3) {
            VoucherEntity v = new VoucherEntity();
            v.setPrezzo(Double.valueOf(String.format(Locale.US, "%.2f", r.getStoricoAcquisti().getPrezzo() * RIMBORSOPIU3GIORNI)));
            return v;
        } else if ((r.getTitoloViaggioEntity().getDataPartenza().toLocalDate().toEpochDay() - r.getDataRichiesta().toEpochDay()) == 1) {
            VoucherEntity v = new VoucherEntity();
            v.setPrezzo(Double.valueOf(String.format(Locale.US, "%.2f", r.getStoricoAcquisti().getPrezzo() * RIMBORSO1GIORNO)));
            return v;
        } else
            return null;

    }
}
