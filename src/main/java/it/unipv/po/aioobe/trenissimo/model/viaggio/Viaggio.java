package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

//TODO: non in UML
public class Viaggio {

    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private List<Connection> cambi;

    private static final AtomicInteger count = new AtomicInteger(0);


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
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(0).getDepartureStation())).findFirst().get();
    }

    public StopsEntity getStazioneArrivo() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(cambi.size() - 1).getArrivalStation())).findFirst().get();
    }

    public int getOrarioPartenza(){
        return cambi.get(0).getDepartureTimestamp();
    }

    public int getOrarioArrivo(){
        return cambi.get(cambi.size() - 1).getArrivalTimestamp();
    }

    public int getDurata() {
        return cambi.get(cambi.size() - 1).getArrivalTimestamp() - cambi.get(0).getDepartureTimestamp();
    }

    public int getNumeroCambi() {
        // TODO: non conta casi in cui il treno torna sulla stessa tratta (eg. R1 -> R2 -> R1 viene contato come un cambio solo invece che due)
        return (int) (cambi.stream().map(x -> x.getDepartureStationTrip()).distinct().count() - 1);
    }

    public double getPrezzo() {

        double prezzoPerPersona=0;

        for (int i=0; i<this.getCambi().size(); i++) {
            prezzoPerPersona = prezzoPerPersona + getDistanza(getCoppiaStazioni(i))*0.15;
        }

        double prezzoTotCambi = prezzoPerPersona + getNumeroCambi()*0.5; //Per ogni cambio si aggiungono 50 centesimi

        //Composizione del prezzo: Adulto prezzo intero, ragazzo 2/3 del prezzo, bambino 1/3 del prezzo, aggiunta di 3 euro per animale

        double prezzoTotPasseggeri = prezzoTotCambi*this.getNumAdulti()+prezzoTotCambi*((double)2/3)*this.getNumRagazzi()+prezzoTotCambi*((double)1/3)*this.getNumBambini()+3*this.getNumAnimali();

        String s = (Utils.secondsToTime(this.getOrarioPartenza()).substring(0,1));


        //Il prezzo per fascia oraria viene calcolato in base alla prima cifra dell'ora e quindi se il viaggio viene fatto prima delle 10:00 o dopo le 19:59, verrà tolto un 25%

        double prezzoFinaleFasciaOraria = prezzoTotPasseggeri - prezzoTotPasseggeri * 0.25 * Math.abs(Integer.valueOf(s)-1);

        return Double.valueOf(String.format(Locale.US,"%.2f", prezzoFinaleFasciaOraria));
    }

    public List<StopsEntity> getCoppiaStazioni (int i) {
        List<StopsEntity> stops = CachedStopsService.getInstance().findAll();
        List<StopsEntity> coppiaStazioni = new ArrayList<>();

        for (StopsEntity s : stops) {
            if (s.getStopId() == this.getCambi().get(i).getDepartureStation() || s.getStopId()==this.getCambi().get(i).getArrivalStation()) {
                coppiaStazioni.add(s);
            }
        }

        return coppiaStazioni;

    }

    public double getDistanza(List<StopsEntity> coppiaStazioni) {

        int raggio = 6371; //raggio Terra approssimato in km

        double lat1 = coppiaStazioni.get(0).getStopLat();
        double lat2 = coppiaStazioni.get(1).getStopLat();
        double lon1 = coppiaStazioni.get(0).getStopLon();
        double lon2 = coppiaStazioni.get(1).getStopLon();

        //Haversine formula to calculate distance from lat e lon

        double latDistance = Math.toRadians(lat1-lat2);
        double lngDistance = Math.toRadians(lon1-lon2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return raggio * c;
    }

    public double getDistanzaTotale () {
        double distanza = 0;
        for (int i=0; i<this.getCambi().size(); i++) {
            distanza = distanza + getDistanza(getCoppiaStazioni(i));
        }
        return Double.valueOf(String.format(Locale.US,"%.2f", distanza));
    }

    @Override
    public String toString() {
        return "Soluzione Viaggio " + count.incrementAndGet() + "\n" +
                "Partenza=" + getStazionePartenza().getStopName() +
                ", h. " + Utils.secondsToTime(getOrarioPartenza()) +
                " -> Arrivo=" + getStazioneArrivo().getStopName() +
                ", h. " + Utils.secondsToTime(getOrarioArrivo()) +
                "\nnumAdulti=" + numAdulti +
                ", numRagazzi=" + numRagazzi +
                ", numBambini=" + numBambini +
                ", numAnimali=" + numAnimali +
                "\nn. cambi=" + getNumeroCambi() +
                "\ndistanza=" + getDistanzaTotale() + "km" +
                "\nprezzo=" + getPrezzo() + "€" +
                "\n";
    }
}
