package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Ricerca implements IRicerca{
    private int stazionePartenza;
    private int stazioneArrivo;
    private LocalDateTime dataPartenza;
    private LocalDateTime dataArrivo;
    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private boolean andataRitorno;

    private List<Viaggio> risultati;

    public Ricerca(int stazionePartenza, int stazioneArrivo, LocalDateTime dataAttuale) {
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.dataPartenza = dataAttuale;
    }

    @Override
    public void eseguiRicerca() {
        CSASearch search = new CSASearch();
        List<Viaggio> lista = search.eseguiRicerca(stazionePartenza, stazioneArrivo);
        lista.forEach((x)->x.setNumAdulti(this.getNumAdulti()));
        lista.forEach((x)->x.setNumAnimali(this.getNumAnimali()));
        lista.forEach((x)->x.setNumRagazzi(this.getNumRagazzi()));
        lista.forEach((x)->x.setNumBambini(this.getNumBambini()));
        lista.forEach((x)->x.setDataPartenza(this.getDataPartenza().toLocalDate()));
        setRisultati(lista);
    }

    public int getNumAdulti() {
        return numAdulti;
    }

    public int getNumRagazzi() {
        return numRagazzi;
    }

    public int getNumBambini() {
        return numBambini;
    }

    public int getNumAnimali() {
        return numAnimali;
    }

    public int getStazionePartenza() {
        return stazionePartenza;
    }

    public int getStazioneArrivo() {
        return stazioneArrivo;
    }

    public LocalDateTime getDataPartenza() {
        return dataPartenza;
    }

    public boolean isAndataRitorno() {
        return andataRitorno;
    }

    public void setNumAdulti(int numAdulti) {
        this.numAdulti = numAdulti;
    }

    public void setNumRagazzi(int numRagazzi) {
        this.numRagazzi = numRagazzi;
    }

    public void setNumBambini(int numBambini) {
        this.numBambini = numBambini;
    }

    public void setNumAnimali(int numAnimali) {
        this.numAnimali = numAnimali;
    }

    public void setAndataRitorno(boolean andataRitorno) {
        this.andataRitorno = andataRitorno;
    }

    public void setStazionePartenza(int stazionePartenza) {
        this.stazionePartenza = stazionePartenza;
    }

    public void setStazioneArrivo(int stazioneArrivo) {
        this.stazioneArrivo = stazioneArrivo;
    }

    public void setDataPartenza(LocalDateTime dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public List<Viaggio> getRisultati() { return risultati; }

    public void setRisultati(List<Viaggio> risultati) { this.risultati = risultati; }

    @Override
    public int compareTo(Viaggio viaggio) {
        return 0;
    }
}
