package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Carnet implements ITitoloViaggio{
    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private String stazionePartenza;
    private String stazioneArrivo;

    public Carnet(DurataTitoloViaggio durata, String stazionePartenza, String stazioneArrivo) {
        this.tipo = TipoTitoloViaggio.CARNET;
        this.durata = durata;
        this.id = "CN" + String.format("%03d", count.incrementAndGet());
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
    }

    //getter e setter
    public String getStazionePartenza() {
        return stazionePartenza;
    }

    public void setStazionePartenza(String stazionePartenza) {
        this.stazionePartenza = stazionePartenza;
    }

    public String getStazioneArrivo() {
        return stazioneArrivo;
    }

    public void setStazioneArrivo(String stazioneArrivo) {
        this.stazioneArrivo = stazioneArrivo;
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

