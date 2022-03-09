package it.unipv.po.aioobe.trenissimo;


import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.CorsaSingola;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.Rimborso;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.TipoTitoloViaggio;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.Voucher;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.Ricerca;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Ricerca search = new Ricerca(858, 1386, LocalDateTime.parse("2022-03-24 12:00:00", formatter));

        search.setNumAdulti(1);
        search.setNumBambini(2);
        search.setNumAnimali(1);
        search.setNumRagazzi(1);

        search.eseguiRicerca();
        List<Viaggio> viaggi = search.getRisultati();

        //Account.getInstance().addViaggioPreferito(viaggi.get(3));
        //System.out.println(viaggi.get(3).getNumeroCambi());




        viaggi.forEach((x)->System.out.println(x.toString()));

        //CorsaSingola biglietto = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(5));
        //orsaSingola biglietto2 = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(5));
        //CorsaSingola biglietto3 = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(3));
        //CorsaSingola biglietto4 = new CorsaSingola(TipoTitoloViaggio.BIGLIETTOCORSASINGOLA, viaggi.get(0));

        //titoloViaggioService.persist(titoloViaggioEntity.toTitoloViaggioEntity(biglietto));

        //titoloViaggioService.findAll().forEach((x)->System.out.println(x.toString()));


        //Account.getInstance().addAcquistoToStorico(biglietto);

        //Account.getInstance().addAcquistoToStorico(biglietto2);


        //TODO FILTRI
        //System.out.println(Utils.floor(14,-1));
        //System.out.println(Utils.ceil(14,-1));

        //Registrazione registrazione = Registrazione.getInstance();
        //registrazione.signUp("vale", "vale", "Valeria", "Vergani", "1997-04-14", "valeria.vergani97@gmail.com", "Via Galliano", "17", "Bresso", "20091");
        //registrazione.signUp("zambo", "zambo", "Fabio", "Zamboni", "1999-07-23", "fabio.zamboni01@universitadipavia.it", "Non lo so", "1", "Vicino Piacenza", "12345");


        //Rimborso r = new Rimborso("CS1646830029004");
        //System.out.println(r.getRimborso().toString());
        //System.out.println(storicoAcquistiService.findAll());




    }

}
