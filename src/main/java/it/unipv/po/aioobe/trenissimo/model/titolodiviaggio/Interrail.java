package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;

public class Interrail implements ITitoloViaggio{

    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private UUID id;

    public Interrail(DurataTitoloViaggio durata, UUID id) {
        this.tipo = TipoTitoloViaggio.INTERRAIL;
        this.durata = durata;
        this.id = id; //da rivedere: id si crea da solo con unmetodo oppure glielo passiamo gia fatto?
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
