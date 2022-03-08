package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public interface IPrezzoTotCambiStrategy {

    double getPrezzoTotCambi(Viaggio v);

}