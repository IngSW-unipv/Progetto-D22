package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Filtro {
    private LocalDate data;
    private LocalTime ora;
    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private int numMaxCambi;
    private ModalitaViaggio modalitaViaggio;

    public Filtro(LocalDate data, LocalTime ora, int numAdulti, int numRagazzi, int numBambini, int numAnimali, int numMaxCambi, ModalitaViaggio modalitaViaggio) {
        this.data = data;
        this.ora = ora;
        this.numAdulti = numAdulti;
        this.numRagazzi = numRagazzi;
        this.numBambini = numBambini;
        this.numAnimali = numAnimali;
        this.numMaxCambi = numMaxCambi;
        this.modalitaViaggio = modalitaViaggio;
    }

    public void setNumMaxCambi(int cambi) {
        this.numMaxCambi = cambi;
    }

    public void setModalitaViaggio(ModalitaViaggio modalitaViaggio) {
        this.modalitaViaggio = modalitaViaggio;
    }

    public void setNumAnimali(int animali) {
        this.numAnimali = animali;
    }

    public void setNumBambini(int bambini) {
        this.numBambini = bambini;
    }

    public void setNumRagazzi(int ragazzi) {
        this.numRagazzi = ragazzi;
    }

    public void setNumAdulti(int adulti) {
        this.numAdulti = adulti;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNumMaxCambi() {
        return this.numMaxCambi;
    }

    public ModalitaViaggio getModalitaViaggio() {
        return this.modalitaViaggio;
    }

    public int getNumAnimali() {
        return this.numAnimali;
    }

    public int getNumBambini() {
        return this.numBambini;
    }

    public int getNumRagazzi() {
        return this.numRagazzi;
    }

    public int getNumAdulti() {
        return this.numAdulti;
    }

    public LocalTime getOra() {
        return this.ora;
    }

    public LocalDate getData() {
        return this.data;
    }
}
