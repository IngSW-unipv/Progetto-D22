package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils;

public class Voucher {
    private String id;
    private double prezzo;

    public Voucher(double prezzo) {
        this.id = "VO" + System.currentTimeMillis();
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

    @Override
    public String toString() {
        return "Voucher{" +
                "id='" + id + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
