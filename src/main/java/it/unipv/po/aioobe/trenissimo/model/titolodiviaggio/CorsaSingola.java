package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.text.Format;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

public class CorsaSingola implements ITitoloViaggio{
    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private Viaggio viaggio;

    public CorsaSingola(DurataTitoloViaggio durata, Viaggio viaggio) {
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
    public DurataTitoloViaggio getDurata() {
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
    public void setDurata(DurataTitoloViaggio durata) {
        this.durata = durata;
    }

    @Override
    public void setPrezzo(double prezzo) {
        // todo
    }
}

