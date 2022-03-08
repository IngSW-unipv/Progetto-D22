package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public interface IPrezzoPerDistanzaStrategy {
    double getPrezzoPerDistanza(Viaggio v);
}
