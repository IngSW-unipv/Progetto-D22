package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.CorsaSingola;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.DurataAbbonamento;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.filtri.FiltroOrario;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

public class App {

    public static void main(String[] args) throws ParseException {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        /*CSASearch search = new CSASearch();
        var viaggi = search.eseguiRicerca(332,2793);
        var result = viaggi.get(0).getCambi();

        System.out.println("Partenza: " + Utils.secondsToTime(viaggi.get(0).getStazionePartenza().getStopId()) + " - Durata: " + Utils.secondsToTime(viaggi.get(0).getDurata()));
        System.out.println("Cambi: " + (result.stream().map(x -> x.getDeparture_station_trip()).distinct().count() - 1));
        for (Connection x : result) {
            var routeFrom = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() ==x.getDeparture_station_trip()).findFirst().get().getRouteId();
            var routeTo = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.getArrival_station_trip()).findFirst().get().getRouteId();
            System.out.println(
                    "[" + routeFrom + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.getDeparture_station()).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.getDeparture_timestamp())
                            + ") -> [" + routeTo + "] " +  CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.getArrival_station()).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.getArrival_timestamp()) + ")");
        }
        System.out.println("\n\n");*/

        /*
        AccountService accountService = new AccountService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();

        System.out.println(accountService.findByUsername("Nyquist").toString());

        DatiPersonaliEntity dati = new DatiPersonaliEntity();

        dati = datiPersonaliService.findByUsername("vale1");

        //dati.setMail("valeria.vergani01@universitadipavia.it");

        datiPersonaliService.update(dati);


        //come se facessimo il login
        var account = Account.getInstance();
        account.setAccount("vale1");
        account.setDatiPersonali("vale1");

        System.out.println(account.getDatiPersonali());
        */
        //viaggiPreferitiService.findByUsername(account.getUsername()).forEach((x)-> System.out.println(x.toString()));
        //storicoAcquistiService.findByUsername(account.getUsername()).forEach((x)->System.out.println(x.toString()));

        /*CorsaSingola biglietto = new CorsaSingola(DurataTitoloViaggio.CORSASINGOLA, viaggi.get(0));

        account.addAcquistoToStorico(biglietto);
        System.out.println("DOPO");
        storicoAcquistiService.findByUsername(account.getUsername()).forEach((x)->System.out.println(x.toString()));*/

        Ricerca search = new Ricerca(332, 2793, LocalDateTime.now());

        search.setNumAdulti(1);
        search.setNumBambini(0);
        search.setNumAnimali(0);
        search.setNumRagazzi(0);

        List<Viaggio> viaggi = search.eseguiRicerca();

        viaggi.forEach((x)->System.out.println(x.toString()));

        FiltroOrario fo = new FiltroOrario(Utils.timeToSeconds("12:22:00"), Utils.timeToSeconds("21:00:00"));

        System.out.println("DOPO\n");

        fo.esegui(viaggi).forEach((x)->System.out.println(x.toString()));

        //TODO FILTRI
       // System.out.println(Utils.floor(14,-1));
       // System.out.println(Utils.ceil(14,-1));
    }

}
