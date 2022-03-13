package it.unipv.po.aioobe.trenissimo;


import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class App {

    public static void main(String[] args) throws ParseException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {

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


        AccountService accountService = new AccountService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();

        Account.login("vale");
        TitoloViaggioEntity titoloViaggioEntity = new TitoloViaggioEntity();



        //Account.signUp("nyquist", "gatto", "Nyquist", "Zambo", "2021-04-30", "nyquist.ilgatto@gmail.com", " A Casa", "6c", "Gattopoli", "77777");

        //accountService.findAll().forEach((x)-> System.out.println(x.toString()));
        //viaggiPreferitiService.findAll().forEach((x)-> System.out.println(x.toString()));
/*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Ricerca search = new Ricerca(1707, 1386, LocalDateTime.parse("2022-03-23 12:00:00", formatter));

        search.setNumAdulti(1);
        search.setNumBambini(0);
        search.setNumAnimali(0);
        search.setNumRagazzi(0);

        search.eseguiRicerca();
        List<Viaggio> viaggi = search.getRisultatiAndata();

        //Account.getInstance().addViaggioPreferito(viaggi.get(3));
        //System.out.println(viaggi.get(3).getNumeroCambi());


       //CorsaSingola biglietto = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(0));
        //CorsaSingola biglietto2 = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(5));
        //CorsaSingola biglietto3 = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(3));

        List<Acquisto> carrello = new ArrayList<>();

        VoucherEntity voucherEntity = new VoucherEntity();
        voucherEntity.setPrezzo(10.0);

        VoucherEntity v2 = new VoucherEntity();
        v2.setPrezzo(100.0);


        VoucherEntity v3 = new VoucherEntity();
        v3.setPrezzo(10.0);


        carrello.add(voucherEntity);
        carrello.add(v2);
        carrello.add(v3);



        //titoloViaggioService.persist(titoloViaggioEntity.toTitoloViaggioEntity(biglietto));

        //titoloViaggioService.findAll().forEach((x)->System.out.println(x.toString()));


        //carrello.forEach(x -> x.acquistare(carrello));

        carrello.forEach(x -> x.pagare());

        new VoucherService().findAll().forEach(x -> System.out.println(x.toString()));

        /*acquisto.acquistare(carrello);
        System.out.println(acquisto.getPrezzoTot());

        for (Acquisto a: acquisto.getAcquisti()) {
            a.pagare();
        }*/

        //System.out.println(accountService.findByUsername("vale").getPuntiFedelta());

        //viaggi.forEach((x)->System.out.println(x.toString()));

        //Account.getInstance().addAcquistoToStorico(biglietto);

        //Account.getInstance().addAcquistoToStorico(biglietto2);

        //System.out.println(Utils.floor(14,-1));
        //System.out.println(Utils.ceil(14,-1));

        //Registrazione registrazione = Registrazione.getInstance();
        //registrazione.signUp("vale", "vale", "Valeria", "Vergani", "1997-04-14", "valeria.vergani97@gmail.com", "Via Galliano", "17", "Bresso", "20091");
        //registrazione.signUp("zambo", "zambo", "Fabio", "Zamboni", "1999-07-23", "fabio.zamboni01@universitadipavia.it", "Non lo so", "1", "Vicino Piacenza", "12345");


        //Rimborso r = new Rimborso("CS1646864674680");
        //VoucherService voucherService = new VoucherService();
        //voucherService.persist(r.getRimborso());

        //voucherService.findAll().forEach((x)->System.out.println(x.toString()));

        //System.out.println(voucherService.findById("VO1646864734159").toString());

        CSASearch search = new CSASearch();
        List<Viaggio> risultati = search.eseguiRicerca(367, 400);
        System.out.println(risultati.size());





    }

}
