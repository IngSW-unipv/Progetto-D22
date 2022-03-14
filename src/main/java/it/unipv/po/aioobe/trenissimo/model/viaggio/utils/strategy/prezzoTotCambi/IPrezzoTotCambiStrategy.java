package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IPrezzoTotCambiStrategy {

    /**
     * Metodo da implementare per poter ottenere il prezzo totale in base al numero di cambi del viaggio passato come parametro.
     *
     * @param v Viaggio
     * @return prezzo (double).
     */
    double getPrezzoTotCambi(Viaggio v);

}