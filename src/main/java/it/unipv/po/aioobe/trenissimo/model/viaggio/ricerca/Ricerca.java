package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ricerca implements IRicerca{
    private ArrayList<Viaggio> risultatiRicerca;
    private int stazionePartenza;
    private int stazioneArrivo;
    private LocalDateTime dataAttuale;

    public Ricerca(int stazionePartenza, int stazioneArrivo, LocalDateTime dataAttuale) {
        this.stazionePartenza = stazionePartenza;
        this.stazioneArrivo = stazioneArrivo;
        this.dataAttuale = dataAttuale;
    }

    @Override
    public List<Viaggio> eseguiRicerca() {
        CSASearch search = new CSASearch();
        List<Viaggio> lista = search.eseguiRicerca(stazionePartenza, stazioneArrivo);
        // TODO: 04/03/2022 importare i dati della ricerca
        //lista.get(0).set
        return lista;

    }

    @Override
    public int compareTo(Viaggio viaggio) {
        return 0;
    }
}
