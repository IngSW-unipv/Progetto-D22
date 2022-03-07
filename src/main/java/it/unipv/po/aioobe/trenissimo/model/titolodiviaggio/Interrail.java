package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Interrail implements ITitoloViaggio{

    private TipoTitoloViaggio tipo;
    private DurataAbbonamento  durata;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);

    public Interrail(DurataAbbonamento  durata) {
        this.tipo = TipoTitoloViaggio.INTERRAIL;
        this.durata = durata;
        this.id = "IR" + String.format("%03d", count.incrementAndGet());
    }

    @Override
    public TipoTitoloViaggio getTipo() {
        return this.tipo;
    }

    public DurataAbbonamento  getDurata() {
        return this.durata;
    }

    @Override
    public double getPrezzo() {
        return this.prezzo; //da calcolare?
    }

    @Override
    public String getTitoloViaggioId() {
        return this.id;
    }

    public void setDurata(DurataAbbonamento  durata) {
        this.durata = durata;
    }

    @Override
    public void setPrezzo(double prezzo) {
        // todo
    }
}
