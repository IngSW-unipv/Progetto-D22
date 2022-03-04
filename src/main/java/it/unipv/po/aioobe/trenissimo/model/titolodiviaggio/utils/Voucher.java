package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Voucher {
    private String id;
    private static final AtomicInteger count = new AtomicInteger(0);
    private double prezzo;

    public Voucher(double prezzo) {
        this.id = "VO" + String.format("%03d", count.incrementAndGet());
        this.prezzo = prezzo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
