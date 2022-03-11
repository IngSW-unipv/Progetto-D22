package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Locale;

public class PrezzoIvaStrategy implements IPrezzoIvaStrategy{

    public final double IVA = (double)22/100;

    @Override
    public double getPrezzoIva(Viaggio v) {
        double prezzo=0;
        prezzo = v.getPrezzoNoIva()*IVA;
        return Double.valueOf(String.format(Locale.US,"%.2f", prezzo));
    }
}
