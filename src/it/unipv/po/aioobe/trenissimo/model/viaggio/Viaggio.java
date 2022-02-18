package src.it.unipv.po.aioobe.trenissimo.model.viaggio;

import src.it.unipv.po.aioobe.trenissimo.model.viaggio.posizione.Posizione;
import src.it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Viaggio implements Comparable<Viaggio>, IDataViaggioUtils{
    private String stazionePartenza;
    private String stazioneArrivo;
    private UUID idTreno;
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

    public Viaggio(String stazionePartenza, String stazioneArrivo, UUID idTreno, Posizione posizionePartenza, Posizione posizioneArrivo) {
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.idTreno = idTreno;
        this.posizionePartenza = posizionePartenza;
        this.posizioneArrivo = posizioneArrivo;
        this.durata = durata; //da calcolare;
        this.prezzo = prezzo; //da calcolare;
    }

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

    public UUID getIdTreno() {
        return idTreno;
    }

    public void setIdTreno(UUID idTreno) {
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

    public double getDurata() {
        return durata;
    }

    public void setDurata(double durata) {
        this.durata = durata;
    }

    public double calcolaPrezzo(Posizione pos, double COSA){ //abbiamo messo double in uml, ma cosa intendevamo? costante?
        return 9; // todo
    }

    @Override
    public int compareTo(Viaggio v) {
        return (int) (this.prezzo-v.prezzo);
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
