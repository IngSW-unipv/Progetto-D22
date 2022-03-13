package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe che modellizza una ricerca completa di un viaggio
 * @author ArrayIndexOutOfBoundsException
 */
public class Ricerca implements IRicerca{
    private int stazionePartenza;
    private int stazioneArrivo;
    private LocalDateTime dataAndata;
    private LocalDateTime dataRitorno;
    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private boolean andataRitorno;

    private List<Viaggio> risultatiAndata;
    private List<Viaggio> risultatiRitorno;

    public Ricerca(int stazionePartenza, int stazioneArrivo, LocalDateTime dataAttuale) {
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.dataAndata = dataAttuale;
    }

    /**
     * Metodo che richiama l'algoritmo di ricerca CSA che esegue una ricerca andando a impostare i risultati nella lista risultatiAndata e/o risultatiRitorno
     */
    @Override
    public void eseguiRicerca() {
        CSASearch andataSearch = new CSASearch();
        List<Viaggio> listaA = andataSearch.eseguiRicerca(stazionePartenza, stazioneArrivo);
        listaA.forEach((x)->x.setNumAdulti(this.getNumAdulti()));
        listaA.forEach((x)->x.setNumAnimali(this.getNumAnimali()));
        listaA.forEach((x)->x.setNumRagazzi(this.getNumRagazzi()));
        listaA.forEach((x)->x.setNumBambini(this.getNumBambini()));
        listaA.forEach((x)->x.setDataPartenza(this.getDataAndata().toLocalDate()));
        setRisultatiAndata(listaA);

        if (andataRitorno){
            CSASearch ritornoSearch = new CSASearch();
            List<Viaggio> listaR = ritornoSearch.eseguiRicerca(stazioneArrivo,stazionePartenza);
            listaR.forEach((x)->x.setNumAdulti(this.getNumAdulti()));
            listaR.forEach((x)->x.setNumAnimali(this.getNumAnimali()));
            listaR.forEach((x)->x.setNumRagazzi(this.getNumRagazzi()));
            listaR.forEach((x)->x.setNumBambini(this.getNumBambini()));
            listaR.forEach((x)->x.setDataPartenza(this.getDataAndata().toLocalDate()));
            setRisultatiRitorno(listaR);
        }
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

    public LocalDateTime getDataAndata() {
        return dataAndata;
    }

    public LocalDateTime getDataRitorno() {
        return dataRitorno;
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

    public void setDataAndata(LocalDateTime dataAndata) {
        this.dataAndata = dataAndata;
    }

    public void setDataRitorno(LocalDateTime dataRitorno) {
        this.dataRitorno = dataRitorno;
    }

    public List<Viaggio> getRisultatiAndata() { return risultatiAndata; }

    public void setRisultatiAndata(List<Viaggio> risultatiAndata) { this.risultatiAndata = risultatiAndata; }

    public List<Viaggio> getRisultatiRitorno() { return risultatiRitorno; }

    public void setRisultatiRitorno(List<Viaggio> risultatiRitorno) { this.risultatiRitorno = risultatiRitorno; }

}
