package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Classe che implementa una strategia per il calcolo dell'IVA
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoIvaStrategy implements IPrezzoIvaStrategy{
    /**
     * Costante alla quale assegniamo il valore dell'IVA (es. 22/100)
     */
    private final double IVA = (double)22/100;
    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata.
     * Metodo che calcola l'IVA del prezzo del Viaggio passato come parametro
     * @param v Viaggio
     */
    @Override
    public double getPrezzoIva(@NotNull Viaggio v) {
        double prezzo=0;
        prezzo = v.getPrezzoNoIva()*IVA;
        return Double.valueOf(String.format(Locale.US,"%.2f", prezzo));
    }
}
