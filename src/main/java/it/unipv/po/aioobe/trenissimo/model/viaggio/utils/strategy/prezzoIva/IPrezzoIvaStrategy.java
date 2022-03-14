package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IPrezzoIvaStrategy {

    /**
     * Metodo da implementare per poter ottenere l'IVA del prezzo del viaggio passato come parametro
     * @param v Viaggio
     */
    public double getPrezzoIva(Viaggio v);

}
