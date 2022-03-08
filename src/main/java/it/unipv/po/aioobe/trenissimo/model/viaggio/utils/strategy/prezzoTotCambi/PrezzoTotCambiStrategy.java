package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public class PrezzoTotCambiStrategy implements IPrezzoTotCambiStrategy{

    private final double PREZZOPERCAMBIO=0.5;

    @Override
    public double getPrezzoTotCambi(Viaggio v){
        double prezzo=0;
        prezzo = prezzo + v.getNumeroCambi()*PREZZOPERCAMBIO; //Per ogni cambio si aggiungono 50 centesimi
        return prezzo;
    }


}
