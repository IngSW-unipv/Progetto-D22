package it.unipv.po.aioobe.trenissimo.model;

import it.unipv.po.aioobe.trenissimo.model.acquisto.*;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.*;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.IRicerca;


import java.util.ArrayList;

public class Trenissimo {
    private static Trenissimo instance;
    private Account profilo;

    private Trenissimo(){ //in singleton il costruttore è privato
        //TO DO
    }

    public static Trenissimo getInstance() {
        if (instance == null)
            instance = new Trenissimo();
        return instance;
    }

    public Account getProfilo() {
        return profilo;
    }

    /*
    public Account creaAccount(String username, String passwordHash){
        // todo
        return new Account();
    }

     */

    public boolean login(String username, String password){
        // todo
        return true;
    }

    public void logout(){
        // todo
    }

    public ArrayList<Viaggio> ricercaViaggio (IRicerca ricerca){
        // todo
        return new ArrayList<Viaggio>();
    }

    public ITitoloViaggio acquistareTitoloViaggio(IPagamento pagamento, Viaggio viaggio){
        // todo
        return null; //da cambiare, chiaramente
    }

    public ITitoloViaggio recuperoTitoloViaggio(String idBiglietto){
        // todo
        return null; //da cambiare, chiaramente
    }
}
