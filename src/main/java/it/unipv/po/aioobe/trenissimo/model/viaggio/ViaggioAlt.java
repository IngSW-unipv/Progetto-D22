package it.unipv.po.aioobe.trenissimo.model.viaggio;

import it.unipv.po.aioobe.trenissimo.model.viaggio.posizione.Posizione;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.ModalitaViaggio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

//TODO: non in UML
public class ViaggioAlt {


    public List<Connection> getCambi() {
        return cambi;
    }

    public void setCambi(List<Connection> cambi) {
        this.cambi = cambi;
    }

    public List<Connection> cambi;

    public ViaggioAlt() {}

    public int getStazionePartenza() {
        return cambi.get(0).departure_station;
    }

    public int getStazioneArrivo() {
        return cambi.get(cambi.size() - 1).arrival_station;
    }

    public int getDurata() {
        return cambi.get(cambi.size() - 1).arrival_timestamp - cambi.get(0).departure_timestamp;
    }

    public int getNumeroCambi() {
        // TODO: non conta casi in cui il treno torna sulla stessa tratta (eg. R1 -> R2 -> R1 viene contato come un cambio solo invece che due)
        return (int) (cambi.stream().map(x -> x.departure_station_trip).distinct().count() - 1);
    }




}
