package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

import java.util.List;
import java.util.Random;

//TODO: non in UML
public class Viaggio {

    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;

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

    public List<Connection> getCambi() {
        return cambi;
    }

    public void setCambi(List<Connection> cambi) {
        this.cambi = cambi;
    }

    public List<Connection> cambi;

    public Viaggio() {}

    public StopsEntity getStazionePartenza() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(0).getDeparture_station())).findFirst().get();
    }

    public StopsEntity getStazioneArrivo() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(cambi.size() - 1).getArrival_station())).findFirst().get();
    }

    public int getOrarioPartenza(){
        return cambi.get(0).getDeparture_timestamp();
    }

    public int getOrarioArrivo(){
        return cambi.get(cambi.size() - 1).getArrival_timestamp();
    }

    public int getDurata() {
        return cambi.get(cambi.size() - 1).getArrival_timestamp() - cambi.get(0).getDeparture_timestamp();
    }

    public int getNumeroCambi() {
        // TODO: non conta casi in cui il treno torna sulla stessa tratta (eg. R1 -> R2 -> R1 viene contato come un cambio solo invece che due)
        return (int) (cambi.stream().map(x -> x.getDeparture_station_trip()).distinct().count() - 1);
    }


    public String getPrezzo() {
        return ((new Random()).nextInt(0,15)) + ",00€";
    }
}
