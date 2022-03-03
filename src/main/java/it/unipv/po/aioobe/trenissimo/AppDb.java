package it.unipv.po.aioobe.trenissimo;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;

import java.sql.Date;
import java.util.logging.Level;

public class AppDb {

    public static void main(String[] args){

        //Per non mostrare in console il log di cfg di hibernate non fondamentali (severe)
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        AccountService accountService = new AccountService();
        /*accountService.findAll().forEach((x)-> System.out.println(x.toString()));


        AgencyService agencyService = new AgencyService();
        agencyService.findAll().forEach((x) -> System.out.println(x.toString()));

        CalendarDatesService calendarDatesService = new CalendarDatesService();
        calendarDatesService.findAll().forEach((x) -> System.out.println(x.toString()));

        CalendarService calendarService = new CalendarService();
        calendarService.findAll().forEach((x) -> System.out.println(x.toString()));

        RoutesService routesService = new RoutesService();
        routesService.findAll().forEach((x) -> System.out.println(x.toString()));


        StopsService stopsService = new StopsService();
        stopsService.findAll().forEach((x) -> System.out.println(x.toString()));


        StopTimesService stopTimesService = new StopTimesService();
        stopTimesService.findAll().forEach((x) -> System.out.println(x.toString()));

        TripsService tripsService = new TripsService();
        tripsService.findAll().forEach((x) -> System.out.println(x.toString()));
        */
        var nome = "Nyquist";
        var pssw = "gatto";

        System.out.println("Inizio\n");

        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();

        //registrazione
        AccountEntity zambo = new AccountEntity();
        DatiPersonaliEntity dati = new DatiPersonaliEntity();
        //login
        zambo.setAccountId(3);


        String myDate = "1999-7-23";
        Date date=Date.valueOf(myDate);

        dati.setDataNascita(date);
        dati.setAccountId(zambo.getAccountId());
        dati.setNome("Fabio");
        dati.setCognome("Zamboni");
        dati.setMail("fabio.zamboni01@universitadipavia.it");
        dati.setIndirizzo("Loc. Albareto 57");
        datiPersonaliService.update(dati);

        storicoAcquistiService.findAll().forEach((x)-> System.out.println(x.toString()));
        accountService.findAll().forEach((x)-> System.out.println(x.toString()));
        System.out.println(datiPersonaliService.findById(zambo.getAccountId().toString()).toString());
        System.out.println("\n");

        System.out.println(accountService.findByUsername("Nyquis") == null ? "NA" : accountService.findByUsername("Nyquis").toString());




    }

}
