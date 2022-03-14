package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

/**
 * Classe che implementa una strategia per il calcolo del prezzo del Viaggio per km considerando tutte le fermate intermedie tra stazione di partenza e stazione di arrivo
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoPerDistanzaStrategy implements IPrezzoPerDistanzaStrategy{
    /**
     * Costante alla quale assegniamo il prezzo per km PREZZOPERKM
     */
    private final double PREZZOPERKM=0.15;
    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata.
     * Metodo che calcola il prezzo del Viaggio per il numero di km
     * @param v Viaggio
     */
    @Override
    public double getPrezzoPerDistanza(Viaggio v) {
        double prezzoPerDistanza = 0;
        for (int i=0; i<v.getCambi().size(); i++) {
            prezzoPerDistanza = prezzoPerDistanza + v.getDistanza(v.getCoppiaStazioni(i))*PREZZOPERKM;
        }
        return prezzoPerDistanza;
    }
}
