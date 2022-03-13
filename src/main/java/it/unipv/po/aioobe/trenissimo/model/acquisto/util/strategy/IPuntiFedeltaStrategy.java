package it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;

/**
 * @author ArrayIndexOutOfBoundsException
 */
public interface IPuntiFedeltaStrategy {

    /**
     * Metodo da implementare per poter ottenere il numero di punti fedeltà ricevuti in base al prezzo dell'acquisto passato come parametro
     * @param acquisto
     */
    public void setPuntiFedelta(Acquisto acquisto);

}
