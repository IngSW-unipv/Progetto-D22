package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.UUID;

public class CorsaSingola implements ITitoloViaggio{
    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private UUID id;
    private Viaggio viaggio;

    public CorsaSingola(DurataTitoloViaggio durata, UUID id, Viaggio viaggio) {
        this.tipo = TipoTitoloViaggio.BIGLIETTOCORSASINGOLA;
        this.durata = durata;
        this.id = id;
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
    public UUID getId() {
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

    @Override
    public void setId(UUID id) {
        // todo
    }
}

