package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IPrezzoTotStrategy {

    /**
     * Metodo da implementare per poter ottenere il prezzo totale del viaggio passato come parametro.
     * @param v Viaggio.
     */
    public double getPrezzoTot(Viaggio v);

}
