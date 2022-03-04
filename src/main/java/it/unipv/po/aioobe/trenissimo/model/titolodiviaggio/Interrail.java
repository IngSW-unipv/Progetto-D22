package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Interrail implements ITitoloViaggio{

    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Interrail(DurataTitoloViaggio durata, String id) {
        this.tipo = TipoTitoloViaggio.INTERRAIL;
        this.durata = durata;
        this.id = "IR" + String.format("%03d", count.incrementAndGet());
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
