package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StopsEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva.IPrezzoIvaStrategy;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva.PrezzoIvaFactory;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza.IPrezzoPerDistanzaStrategy;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza.PrezzoPerDistanzaFactory;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot.IPrezzoTotStrategy;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot.PrezzoTotFactory;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi.IPrezzoTotCambiStrategy;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi.PrezzoTotCambiFactory;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe che modellizza un viaggio.
 * @author ArrayIndexOutOfBoundsException
 */
public class Viaggio {

    private int numAdulti;
    private int numRagazzi;
    private int numBambini;
    private int numAnimali;
    private LocalDate dataPartenza;
    private List<Connection> cambi;
    private IPrezzoPerDistanzaStrategy prezzoPerDistanzaStrategy;
    private IPrezzoTotCambiStrategy prezzoTotCambiStrategy;
    private IPrezzoTotStrategy prezzoTotStrategy;
    private IPrezzoIvaStrategy prezzoIvaStrategy;

    private static final AtomicInteger count = new AtomicInteger(0);


    /**
     * Costruttore del viaggio. Vengono istanziate le diverse strategy per il calcolo del prezzo totale.
     */
    public Viaggio() {

        PrezzoPerDistanzaFactory f = new PrezzoPerDistanzaFactory();
        prezzoPerDistanzaStrategy = f.getPrezzoPerDistanzaStrategy();

        PrezzoTotCambiFactory f1 = new PrezzoTotCambiFactory();
        prezzoTotCambiStrategy = f1.getPrezzoTotCambiStrategy();

        PrezzoTotFactory f2 = new PrezzoTotFactory();
        prezzoTotStrategy = f2.getPrezzoTot();

        PrezzoIvaFactory f3 = new PrezzoIvaFactory();
        prezzoIvaStrategy = f3.getPrezzoIvaStrategy();

    }

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

    public LocalDate getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(LocalDate dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public List<Connection> getCambi() {
        return cambi;
    }

    public void setCambi(List<Connection> cambi) {
        this.cambi = cambi;
    }

    /**
     * Metodo per ottenere la stazione di partenza dalla classe CachedStopsService
     * @return StopsEntity
     */
    public StopsEntity getStazionePartenza() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(0).getDepartureStation())).findFirst().get();
    }

    /**
     * Metodo per ottenere la stazione di arrivo dalla classe CachedStopsService
     * @return StopsEntity
     */
    public StopsEntity getStazioneArrivo() {
        return CachedStopsService.getInstance().findAll().stream().filter(x -> x.getStopId() == (cambi.get(cambi.size() - 1).getArrivalStation())).findFirst().get();
    }

    public int getOrarioPartenza() {
        return cambi.get(0).getDepartureTimestamp();
    }

    public int getOrarioArrivo() {
        return cambi.get(cambi.size() - 1).getArrivalTimestamp();
    }

    public int getDurata() {
        return cambi.get(cambi.size() - 1).getArrivalTimestamp() - cambi.get(0).getDepartureTimestamp();
    }

    public int getNumeroCambi() {
        // non conta casi in cui il treno torna sulla stessa tratta (eg. R1 -> R2 -> R1 viene contato come un cambio solo invece che due)
        return (int) (cambi.stream().map(x -> x.getDepartureStationTrip()).distinct().count() - 1);
    }

    public double getPrezzoPerDistanza() {

        return prezzoPerDistanzaStrategy.getPrezzoPerDistanza(this);

    }

    public double getPrezzoTotCambi() {

        return prezzoTotCambiStrategy.getPrezzoTotCambi(this);

    }

    public double getPrezzoPerPersona() {
        return this.getPrezzoPerDistanza() + this.getPrezzoTotCambi();
    }

    public double getPrezzoNoIva() {

        return prezzoTotStrategy.getPrezzoTot(this);

    }

    public double getPrezzoIva() {
        return prezzoIvaStrategy.getPrezzoIva(this);
    }

    public double getPrezzoTot() {
        return Double.valueOf(getPrezzoTotString());
    }

    public String getPrezzoTotString() {
        return String.format(Locale.US, "%.2f", this.getPrezzoNoIva() + this.getPrezzoIva());
    }

    /**
     * Metodo che viene utilizzato per ottenere una coppia di stazioni.
     * @param i indice (in quanto utilizzato all'interno di un ciclo for).
     * @return List<StopsEntity>
     */
    public List<StopsEntity> getCoppiaStazioni(int i) {
        List<StopsEntity> stops = CachedStopsService.getInstance().findAll();
        List<StopsEntity> coppiaStazioni = new ArrayList<>();

        for (StopsEntity s : stops) {
            if (s.getStopId() == this.getCambi().get(i).getDepartureStation() || s.getStopId() == this.getCambi().get(i).getArrivalStation()) {
                coppiaStazioni.add(s);
            }
        }

        return coppiaStazioni;

    }

    /**
     * Metodo utilizzato per ottenere la distanza tra una coppia di stazioni.
     * @param coppiaStazioni
     * @return distanza (double). <br>
     * @see <a href="https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula">Link</a>
     */
    public double getDistanza(@NotNull List<StopsEntity> coppiaStazioni) {

        int raggio = 6371; //raggio Terra approssimato in km

        double lat1 = coppiaStazioni.get(0).getStopLat();
        double lat2 = coppiaStazioni.get(1).getStopLat();
        double lon1 = coppiaStazioni.get(0).getStopLon();
        double lon2 = coppiaStazioni.get(1).getStopLon();

        //Haversine formula to calculate distance from lat e lon

        double latDistance = Math.toRadians(lat1 - lat2);
        double lngDistance = Math.toRadians(lon1 - lon2);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return raggio * c;
    }

    /**
     * Metodo che viene utilizzato per ottenere la distanza totale del viaggio, tenendo conto dei cambi.
     * @return distanza (Double)
     */
    public double getDistanzaTotale() {
        double distanza = 0;
        for (int i = 0; i < this.getCambi().size(); i++) {
            distanza = distanza + getDistanza(getCoppiaStazioni(i));
        }
        return Double.valueOf(String.format(Locale.US, "%.2f", distanza));
    }

    @Override
    public String toString() {
        return "Soluzione Viaggio " + count.incrementAndGet() + "\n" +
                "Partenza=" + getStazionePartenza().getStopName() +
                ", h. " + Utils.secondsToTime(getOrarioPartenza()) +
                "data=" + getDataPartenza().toString() +
                " -> Arrivo=" + getStazioneArrivo().getStopName() +
                ", h. " + Utils.secondsToTime(getOrarioArrivo()) +
                "\nnumAdulti=" + numAdulti +
                ", numRagazzi=" + numRagazzi +
                ", numBambini=" + numBambini +
                ", numAnimali=" + numAnimali +
                "\nn. cambi=" + getNumeroCambi() +
                "\ndistanza=" + getDistanzaTotale() + "km" +
                "\nprezzo senza IVA=" + getPrezzoTot() + "€" +
                "\nprezzo con IVA=" + Double.valueOf(String.format(Locale.US, "%.2f", getPrezzoTot() + getPrezzoIva())) + "€" +
                "\n";
    }
}
