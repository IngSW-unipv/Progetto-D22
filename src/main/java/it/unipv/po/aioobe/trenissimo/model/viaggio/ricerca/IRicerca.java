package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Comparator;
import java.util.List;

public interface IRicerca extends Comparable<Viaggio> {
    public List<Viaggio> eseguiRicerca();
    public int compareTo(Viaggio viaggio);
}
