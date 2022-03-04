package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.posizione.Posizione;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Viaggio implements Comparable<Viaggio> {
    private String stazionePartenza;
    private String stazioneArrivo;
    private String idTreno;
    private Posizione posizionePartenza;
    private Posizione posizioneArrivo;
    private double prezzo;
    private double durata; //non esiste la classe Time;
    private LocalDate data;
    private LocalTime ora;
    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private int numMaxCambi;
    private ModalitaViaggio modalitaViaggio;
    private List<Connection> cambi; //TODO: non in UML

    public Viaggio(String stazionePartenza, String stazioneArrivo, String idTreno,
                   Posizione posizionePartenza, Posizione posizioneArrivo) {
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.idTreno = idTreno;
        this.posizionePartenza = posizionePartenza;
        this.posizioneArrivo = posizioneArrivo;
        this.durata = durata; //da calcolare;
        this.prezzo = prezzo; //da calcolare: distanza in linea d'aria*prezzo al km;
    }

    public Viaggio() {}

    public void setStazionePartenza(String stazionePartenza) {
        this.stazionePartenza = stazionePartenza;
    }


    public void setStazioneArrivo(String stazioneArrivo) {
        this.stazioneArrivo = stazioneArrivo;
    }

    public String getIdTreno() {
        return idTreno;
    }

    public void setIdTreno(String idTreno) {
        this.idTreno = idTreno;
    }

    public Posizione getPosizionePartenza() {
        return posizionePartenza;
    }

    public void setPosizionePartenza(Posizione posizionePartenza) {
        this.posizionePartenza = posizionePartenza;
    }

    public Posizione getPosizioneArrivo() {
        return posizioneArrivo;
    }

    public void setPosizioneArrivo(Posizione posizioneArrivo) {
        this.posizioneArrivo = posizioneArrivo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getDurata() {
        return cambi.get(cambi.size() - 1).arrival_timestamp - cambi.get(0).departure_timestamp;
    }

    public void setDurata(double durata) {
        this.durata = durata;
    }

    public double calcolaPrezzo(Posizione pos, double COSA) { //abbiamo messo double in uml, ma cosa intendevamo? costante?
        return 9; // todo
    }

    @Override
    public int compareTo(Viaggio v) {
        return (int) (this.prezzo - v.prezzo);
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

    public List<Connection> getCambi() {
        return cambi;
    } //TODO: non in IDataViaggioUtils

    public void setCambi(List<Connection> cambi) {
        this.cambi = cambi;
    } //TODO: non in IDataViaggioUtils

    public int getNumeroCambi() {
        // TODO: non conta casi in cui il treno torna sulla stessa tratta (eg. R1 -> R2 -> R1 viene contato come un cambio solo invece che due)
        return (int) (cambi.stream().map(x -> x.departure_station_trip).distinct().count() - 1);
    }

    public int getOrarioPartenza(){
        return cambi.get(0).departure_timestamp;
    }

    public int getOrarioArrivo(){
        return cambi.get(cambi.size() - 1).arrival_timestamp;
    }

    public StopsEntity getStazionePartenza() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(0).departure_station)).findFirst().get();
    }

    public StopsEntity getStazioneArrivo() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(cambi.size() - 1).arrival_station)).findFirst().get();
    }
}
