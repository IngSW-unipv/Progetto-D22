package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

import java.util.List;
import java.util.Locale;

//TODO: non in UML
public class Viaggio {

    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private List<Connection> cambi;

    public Viaggio() {}

    public int getNumAdulti() {
        return numAdulti;
    }

    public void setNumAdulti(int numAdulti) {
        this.numAdulti = numAdulti;
    }

    public int getNumRagazzi() {
        return numRagazzi;
    }

    public void setNumRagazzi(int numRagazzi) {
        this.numRagazzi = numRagazzi;
    }

    public int getNumBambini() {
        return numBambini;
    }

    public void setNumBambini(int numBambini) {
        this.numBambini = numBambini;
    }

    public int getNumAnimali() {
        return numAnimali;
    }

    public void setNumAnimali(int numAnimali) {
        this.numAnimali = numAnimali;
    }

    public List<Connection> getCambi() {
        return cambi;
    }

    public void setCambi(List<Connection> cambi) {
        this.cambi = cambi;
    }

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

    public double getPrezzo() {

        double prezzoPerPersona = getDistanza()*0.10; //10 centesimi a km

        //Composizione del prezzo: Adulto prezzo intero, ragazzo 2/3 del prezzo, bambino 1/3 del prezzo, aggiunta di 5 euro per animale

        double prezzo = prezzoPerPersona*this.getNumAdulti()+prezzoPerPersona*((double)2/3)*this.getNumRagazzi()+prezzoPerPersona*((double)1/3)*this.getNumBambini()+5*this.getNumAnimali();

        return Double.valueOf(String.format(Locale.US,"%.2f", prezzo));
    }

    public double getDistanza() {

        int raggio = 6371; //raggio Terra approssimato in km

        double lat1 = this.getStazionePartenza().getStopLat();
        double lat2 = this.getStazioneArrivo().getStopLat();
        double lon1 = this.getStazionePartenza().getStopLon();
        double lon2 = this.getStazioneArrivo().getStopLon();

        //Haversine formula to calculate distance from lat e lon

        double latDistance = Math.toRadians(lat1-lat2);
        double lngDistance = Math.toRadians(lon1-lon2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return raggio * c;
    }
}
