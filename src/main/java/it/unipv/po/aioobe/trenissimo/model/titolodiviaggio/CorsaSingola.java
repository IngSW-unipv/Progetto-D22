package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.concurrent.atomic.AtomicInteger;

public class CorsaSingola implements ITitoloViaggio{
    private TipoTitoloViaggio tipo;
    private DurataAbbonamento  durata;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private Viaggio viaggio;

    public CorsaSingola(DurataAbbonamento  durata, Viaggio viaggio) {
        this.tipo = TipoTitoloViaggio.BIGLIETTOCORSASINGOLA;
        this.durata = durata;
        this.id = "CS" + String.format("%03d", count.incrementAndGet());
        this.viaggio = viaggio;
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
    public DurataAbbonamento  getDurata() {
        return this.durata;
    }

    @Override
    public double getPrezzo() {
        return this.prezzo; //da calcolare?
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setDurata(DurataAbbonamento  durata) {
        this.durata = durata;
    }

    @Override
    public void setPrezzo(double prezzo) {
        // todo
    }
}

