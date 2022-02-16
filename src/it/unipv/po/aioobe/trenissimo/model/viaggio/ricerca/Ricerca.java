package src.it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import src.it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Ricerca implements IRicerca{
    private ArrayList<Viaggio> risultatiRicerca;
    private String stazionePartenza;
    private String stazioneArrivo;
    private LocalDateTime dataAttuale;

    public Ricerca(ArrayList<Viaggio> risultatiRicerca, String stazionePartenza, String stazioneArrivo, LocalDateTime dataAttuale) {
        this.risultatiRicerca = risultatiRicerca;
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.dataAttuale = dataAttuale;
    }

    @Override
    public ArrayList<Viaggio> eseguiRicerca(IRicerca ricerca) {
        return null;
    }

    @Override
    public int compareTo(Viaggio viaggio) {
        return 0;
    }

    @Override
    public void ordinaByPrezzo() {

    }

    @Override
    public void ordinaByDurataViaggio(Comparator<Viaggio> comparator) {

    }
}
