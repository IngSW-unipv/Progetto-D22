package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che implementa una strategia per il calcolo del prezzo totale del viaggio, in base al numero di cambi.
 * @author ArrayIndexOutOfBoundsException
 */

public class PrezzoTotCambiStrategy implements IPrezzoTotCambiStrategy{

    private final double PREZZOPERCAMBIO=0.5;

    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata.
     * Metodo che calcola il prezzo totale del viaggio passato come parametro, in base al numero di cambi che fa.<br>
     * In particolare, se un viaggio ha un numero di cambi superiore a 2, si tolgono 50 centesimi dal totale per ogni cambio.
     * @param v Viaggio
     * @return prezzo (double): Il prezzo sarà un numero negativo, che andra sommato al prezzo totale al momento opportuno.
     */
    @Override
    public double getPrezzoTotCambi(@NotNull Viaggio v){
        double prezzo=0;
        if (v.getNumeroCambi() > 2)
            prezzo = prezzo - v.getNumeroCambi() * PREZZOPERCAMBIO; //Per ogni cambio si tolgono 50 centesimi dal totale
            return prezzo;
    }


}
