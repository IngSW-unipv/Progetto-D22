package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public interface IPrezzoIvaStrategy {

    public double getPrezzoIva(Viaggio v);

}
