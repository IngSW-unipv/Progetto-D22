package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Classe che implementa una strategia per il calcolo del prezzo totale di un viaggio in base al numero di adulti, ragazzi, bambini e animali.
 *
 * @author ArrayIndexOutOfBoundsException
 */

public class PrezzoTotStrategy implements IPrezzoTotStrategy {

    private final double COEFFICIENTEBAMBINI = (double) 1 / 3;
    private final double COEFFICIENTERAGAZZI = (double) 2 / 3;
    private final double PREZZOPERANIMALE = 3.0;

    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata.
     * Metodo che calcola il prezzo totale del viaggio passato come parametro, in base al numero di adulti, numero di ragazzi,
     * numero di bambini, numero di animali.
     * Rispettivamente: Adulti: prezzo intero. <br>
     * Ragazzi: 2/3 del prezzo intero. <br>
     * Bambini: 1/3 del prezzo intero <br>
     * Animali: 3€ per ogni animale portato.
     *
     * @param v Viaggio.
     * @return prezzo (double).
     */
    @Override
    public double getPrezzoTot(@NotNull Viaggio v) {
        double prezzo = 0;
        prezzo = v.getPrezzoPerPersona() * v.getNumAdulti() + v.getPrezzoPerPersona() * COEFFICIENTERAGAZZI * v.getNumRagazzi() + v.getPrezzoPerPersona() * COEFFICIENTEBAMBINI * v.getNumBambini() + PREZZOPERANIMALE * v.getNumAnimali();
        return Double.valueOf(String.format(Locale.US, "%.2f", prezzo));
    }

}
