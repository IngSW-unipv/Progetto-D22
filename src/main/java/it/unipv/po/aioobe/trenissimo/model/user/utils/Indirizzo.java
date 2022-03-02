package it.unipv.po.aioobe.trenissimo.model.user.utils;

public class Indirizzo {
    private String via;
    private String civico;
    private String citta;
    private String cap;

    public Indirizzo(String via, String civico, String citta, String cap) {
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

}
