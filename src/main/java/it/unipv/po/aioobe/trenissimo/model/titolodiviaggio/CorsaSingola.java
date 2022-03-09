package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

public class CorsaSingola implements ITitoloViaggio {
    private TipoTitoloViaggio tipo;
    private double prezzo;
    private String id;
    private Viaggio viaggio;

    public CorsaSingola(TipoTitoloViaggio tipo, Viaggio viaggio) {
        this.tipo = TipoTitoloViaggio.BIGLIETTOCORSASINGOLA;
        this.tipo = tipo;
        this.id = "CS" + System.currentTimeMillis();
        this.viaggio = viaggio;
        this.prezzo = viaggio.getPrezzoTot();
    }

    //getter
    public Viaggio getViaggio() {
        return viaggio;
    }

    @Override
    public TipoTitoloViaggio getTipo() {
        return this.tipo;
    }

    @Override
    public double getPrezzo() {
        return this.prezzo;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

}

