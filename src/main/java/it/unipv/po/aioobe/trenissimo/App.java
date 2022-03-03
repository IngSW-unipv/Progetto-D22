package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

import java.text.ParseException;
import java.util.UUID;
import java.util.logging.Level;

public class App {

    public static void main(String[] args) throws ParseException {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        CSASearch search = new CSASearch();
        var viaggi = search.eseguiRicerca(332,2793);
        var result = viaggi.get(0).getCambi();

        System.out.println("Partenza: " + Utils.secondsToTime(viaggi.get(0).getStazionePartenza()) + " - Durata: " + Utils.secondsToTime(viaggi.get(0).getDurata()));
        System.out.println("Cambi: " + (result.stream().map(x -> x.departure_station_trip).distinct().count() - 1));
        for (Connection x : result) {
            var routeFrom = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() ==x.departure_station_trip).findFirst().get().getRouteId();
            var routeTo = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.arrival_station_trip).findFirst().get().getRouteId();
            System.out.println(
                    "[" + routeFrom + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.departure_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.departure_timestamp)
                            + ") -> [" + routeTo + "] " +  CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.arrival_station).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.arrival_timestamp) + ")");
        }
        System.out.println("\n\n");

        AccountService accountService = new AccountService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();

        var account = Account.getInstance(accountService.findById("3"), datiPersonaliService.findById("3"));
        System.out.println(account.getDatiPersonali());

        //account.addViaggioPreferito(viaggi.get(0));

        viaggiPreferitiService.findByAccount(account.getId().toString()).forEach((x)-> System.out.println(x.toString()));



    }

}
