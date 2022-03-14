package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IPrezzoPerDistanzaStrategy {
    /**
     * Metodo da implementare per poter ottenere il prezzo del Viaggio da un prezzo fisso per km tra la stazione di partenza e quella di arrivo considerando tutte le fermate intermedie
     * @param v Viaggio
     */
    public double getPrezzoPerDistanza(Viaggio v);
}
