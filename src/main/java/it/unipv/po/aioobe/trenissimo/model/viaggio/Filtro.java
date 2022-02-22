package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Filtro implements IDataViaggioUtils {
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

    @Override
    public void setNumMaxCambi(int cambi) {
        this.numMaxCambi = cambi;
    }

    @Override
    public void setModalitaViaggio(ModalitaViaggio modalitaViaggio) {
        this.modalitaViaggio = modalitaViaggio;
    }

    @Override
    public void setNumAnimali(int animali) {
        this.numAnimali = animali;
    }

    @Override
    public void setNumBambini(int bambini) {
        this.numBambini = bambini;
    }

    @Override
    public void setNumRagazzi(int ragazzi) {
        this.numRagazzi = ragazzi;
    }

    @Override
    public void setNumAdulti(int adulti) {
        this.numAdulti = adulti;
    }

    @Override
    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    @Override
    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public int getNumMaxCambi() {
        return this.numMaxCambi;
    }

    @Override
    public ModalitaViaggio getModalitaViaggio() {
        return this.modalitaViaggio;
    }

    @Override
    public int getNumAnimali() {
        return this.numAnimali;
    }

    @Override
    public int getNumBambini() {
        return this.numBambini;
    }

    @Override
    public int getNumRagazzi() {
        return this.numRagazzi;
    }

    @Override
    public int getNumAdulti() {
        return this.numAdulti;
    }

    @Override
    public LocalTime getOra() {
        return this.ora;
    }

    @Override
    public LocalDate getData() {
        return this.data;
    }
}
