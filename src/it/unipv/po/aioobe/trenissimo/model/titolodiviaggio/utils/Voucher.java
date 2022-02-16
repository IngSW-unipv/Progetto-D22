package src.it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;

import java.util.UUID;

public class Voucher {
    private UUID id;
    private double valore;

    public Voucher(double valore) {
        this.id = id; //da calcolare
        this.valore = valore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }
}
