package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Locale;

public class PrezzoTotStrategy implements IPrezzoTotStrategy {

    public final double COEFFICIENTEBAMBINI=(double)1/3;
    public final double COEFFICIENTERAGAZZI=(double)2/3;
    public final double PREZZOPERANIMALE=3.0;

    @Override
    public double getPrezzoTot(Viaggio v) {
        double prezzo=0;
        prezzo = v.getPrezzoPerPersona()*v.getNumAdulti()+v.getPrezzoPerPersona()*COEFFICIENTERAGAZZI*v.getNumRagazzi()+v.getPrezzoPerPersona()*COEFFICIENTEBAMBINI*v.getNumBambini()+PREZZOPERANIMALE*v.getNumAnimali();
        return Double.valueOf(String.format(Locale.US,"%.2f", prezzo));
    }

}
