package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public class PrezzoPerDistanzaStrategy implements IPrezzoPerDistanzaStrategy{

    private final double PREZZOPERKM=0.15;

    @Override
    public double getPrezzoPerDistanza(Viaggio v) {
        double prezzoPerDistanza = 0;
        for (int i=0; i<v.getCambi().size(); i++) {
            prezzoPerDistanza = prezzoPerDistanza + v.getDistanza(v.getCoppiaStazioni(i))*PREZZOPERKM;
        }
        return prezzoPerDistanza;
    }
}
