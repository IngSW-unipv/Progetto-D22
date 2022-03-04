package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.IDataViaggioUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;

public class Account {
    private static Account instance;
    private DatiPersonaliEntity datiPersonali;
    private String username;
    private String passwordHash;
    private ArrayList<IDataViaggioUtils> viaggiPreferiti;
    private ArrayList<IAcquisto> storicoAcquisti;

    //getter
    public static Account getInstance() {
        if (instance == null)
            instance = new Account();
        return instance;
    }

    public DatiPersonaliEntity getDatiPersonali(String username) {
        return this.datiPersonali = new DatiPersonaliService().findByUsername(username);
    }

    public String getUsername() {
        return "zambo";
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public ArrayList<IDataViaggioUtils> getViaggiPreferiti() {
        return viaggiPreferiti;
    }

    public ArrayList<IAcquisto> getStoricoAcquisti() {
        return storicoAcquisti;
    }

    //setter
    public void setDatiPersonali(DatiPersonaliEntity datiPersonali) {
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





    public void salva(String nome, String cognome, LocalDate dataNascita, String mail, String via, String civico, String citta, String cap){
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        datiPersonali.setNome(nome);
        datiPersonali.setCognome(cognome);
        datiPersonali.setDataNascita(Date.valueOf(dataNascita));
        datiPersonali.setMail(mail);
        /*
        datiPersonali.setVia(via);
        datiPersonali.setCivico(civico);
        datiPersonali.setCitta(citta);
        datiPersonali.setCAP(cap);
        */
        datiPersonaliService.update(datiPersonali);
    }
    public boolean checkCAP(String CAP){
        return CAP.length() == 5 && CAP.matches("^[0-9]+$");
    }

    public boolean checkEmail(String email){

        return email.matches("[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}");
    }

    public boolean checkDataNascita(LocalDate data){
        return data.isBefore(LocalDate.now());
    }

    public boolean checkDatiGenerico(String dato){
        return dato.length() > 0;
    }










}
