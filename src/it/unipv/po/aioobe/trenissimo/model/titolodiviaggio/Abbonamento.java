package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;

public class Abbonamento implements ITitoloViaggio{
    private TipoTitoloViaggio tipo;
    private DurataTitoloViaggio durata;
    private double prezzo;
    private UUID id;
    private String stazionePartenza;
    private String stazioneArrivo;

    public Abbonamento(DurataTitoloViaggio durata, UUID id, String stazionePartenza, String stazioneArrivo) {
        this.tipo = TipoTitoloViaggio.ABBONAMENTO;
        this.durata = durata;
        this.id = id; //da vedere, come in interrail
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
