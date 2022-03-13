package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

/**
 * Interfaccia che stabilisce le signature dei metodi che verranno implementati per effettuare il filtraggio dei risultati di una ricerca
 * @author ArrayIndexOutOfBoundsException
 */
public interface IFiltro {
    public List<Viaggio> esegui(List<Viaggio> input);
}