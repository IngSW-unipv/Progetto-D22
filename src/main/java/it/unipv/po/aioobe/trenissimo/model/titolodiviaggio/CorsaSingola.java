package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.concurrent.atomic.AtomicInteger;

public class CorsaSingola extends TitoloViaggioEntity implements ITitoloViaggio {
    private TipoTitoloViaggio tipo;
    private double prezzo;
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private Viaggio viaggio;

    public CorsaSingola(TipoTitoloViaggio tipo, Viaggio viaggio) {
        this.tipo = TipoTitoloViaggio.BIGLIETTOCORSASINGOLA;
        this.tipo = tipo;
        this.id = "CS" + String.format("%03d", count.incrementAndGet());
        this.viaggio = viaggio;
        this.prezzo = viaggio.getPrezzo();
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
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}

