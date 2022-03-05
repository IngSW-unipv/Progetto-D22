package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public interface IFiltro {
    public List<Viaggio> esegui(List<Viaggio> input);
}