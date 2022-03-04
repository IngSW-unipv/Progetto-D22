package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;
import org.hibernate.type.UUIDBinaryType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();

        System.out.println(accountService.findByUsername("Nyquist").toString());

        AccountEntity account1 = new AccountEntity();
        account1.setUsername("vale1");
        account1.setPassword("vale1");

        /*try {
            accountService.persist(account1);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        StoricoAcquistiEntity storico1 = new StoricoAcquistiEntity();
        storico1.setUsername("vale1");
        storico1.setTitoloViaggioId(UUID.randomUUID().toString());
        storico1.setPrezzo(4.50);

        storicoAcquistiService.persist(storico1);

        /*try {
            accountService.deleteByUsername("vale");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        DatiPersonaliEntity dati = new DatiPersonaliEntity();
        /*dati.setUsername("vale1");
        dati.setNome("Valeria");
        dati.setCognome("Vergani");
        dati.setCap(20091);
        dati.setCitta("Bresso");
        dati.setVia("Via Galliano");
        dati.setCivico(17);
        dati.setDataNascita(Date.valueOf("1997-04-14"));
        dati.setMail("valeria.vergani97@gmail.com");*/

        //datiPersonaliService.persist(dati);

        dati = datiPersonaliService.findByUsername("vale1");

        dati.setMail("valeria.vergani01@universitadipavia.it");

        datiPersonaliService.update(dati);


        //come se facessimo il login
        var account = Account.getInstance();
        account.setAccount("vale1");
        account.setDatiPersonali("vale1");

        System.out.println(account.getDatiPersonali());

        //account.addViaggioPreferito(viaggi.get(0));

        viaggiPreferitiService.findByUsername(account.getUsername()).forEach((x)-> System.out.println(x.toString()));
        storicoAcquistiService.findByUsername(account.getUsername()).forEach((x)->System.out.println(x.toString()));

        //TODO ELIMINARE INTERFACCE E CLASSI INUTILI TIPO IDATAVIAGGIOUTILS, VIAGGIOPREFERITO, VIAGGIOALT, DATIPERSONALI
        //TODO FILTRI
        //TODO SPOSTARE COMAPARATORDURATAVIAGGIO IN FILTRO
        //TODO SISTEMARE CLASSE VIAGGIO E VEDERE COME IMPOSTARE IL PREZZO


    }

}
