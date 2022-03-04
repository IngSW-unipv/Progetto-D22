package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;

import java.util.UUID;

public class Voucher {
    private UUID id;
    private double prezzo;

    public Voucher(double prezzo) {
        this.id = id; //da calcolare
        this.prezzo = prezzo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
