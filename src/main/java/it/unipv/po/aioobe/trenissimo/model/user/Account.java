package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.viaggio.IDataViaggioUtils;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private static Account instance;
    private DatiPersonali datiPersonali;
    private String username;
    private String passwordHash;
    private UUID id;
    private ArrayList<IDataViaggioUtils> viaggiPreferiti;
    private ArrayList<IAcquisto> storicoAcquisti;

    //getter
    public static Account getInstance() {
        if (instance == null)
            instance = new Account();
        return instance;
    }

    public DatiPersonali getDatiPersonali() {
        return datiPersonali;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UUID getId() {
        return id;
    }

    public ArrayList<IDataViaggioUtils> getViaggiPreferiti() {
        return viaggiPreferiti;
    }

    public ArrayList<IAcquisto> getStoricoAcquisti() {
        return storicoAcquisti;
    }

    //setter
    public void setDatiPersonali(DatiPersonali datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    public void setPassword(String username, String password) {
        this.passwordHash = passwordHash;
    }

    public void addViaggioPreferito(IDataViaggioUtils viaggio){
        // todo
    }

    public void storicoAcquisti(IAcquisto acquisto){
        // todo
    }
}
