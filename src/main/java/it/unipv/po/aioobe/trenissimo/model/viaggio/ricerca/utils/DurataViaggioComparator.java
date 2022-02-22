package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Comparator;

public class DurataViaggioComparator implements Comparator<Viaggio> {

    @Override
    public int compare(Viaggio v1, Viaggio v2) {
        return (int) (v1.getDurata()-v2.getDurata());
    }
}
