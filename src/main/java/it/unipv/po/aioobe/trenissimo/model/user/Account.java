package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.CryptographyUtils;
import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import javafx.beans.property.SimpleBooleanProperty;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;


public class Account {

    private static Account instance;
    private AccountEntity account;
    private DatiPersonaliEntity datiPersonali;


    public static SimpleBooleanProperty loggedInProperty = new SimpleBooleanProperty(false);

    public static boolean getLoggedIn() {
        return loggedInProperty.get();
    }

    public static void setLoggedIn(boolean loggedIn) {
        loggedInProperty.set(loggedIn);
    }

    public static Account getInstance() {
        if (instance == null)
            instance = new Account();
        return instance;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(String accountId) {
        AccountService accountService = new AccountService();
        this.account = accountService.findByUsername(accountId);
    }

    public DatiPersonaliEntity getDatiPersonali() {
        return this.datiPersonali;
    }

    public void setDatiPersonali(String user) {
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        this.datiPersonali = datiPersonaliService.findByUsername(user);
    }

    public String getUsername() {
        return this.account.getUsername();
    }

    /*public String getPassword() {
        return this.account.getPassword();
    }*/

    public void setUsername(String username) {
        this.account.setUsername(username);
    }

    public void setPassword(String password) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.account.setPassword(CryptographyUtils.encryptPassword(password));
        AccountService accountService = new AccountService();
        accountService.update(this.account);
    }

    public String getPuntiFedelta(){
        return this.account.getPuntiFedelta().toString();
    }

    public boolean salvaModificaDati(String nome, String cognome, LocalDate dataNascita, String mail, String via, String civico, String citta, String cap){
        try {
            DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
            datiPersonali.setNome(nome);
            datiPersonali.setCognome(cognome);
            datiPersonali.setDataNascita(Date.valueOf(dataNascita));
            datiPersonali.setMail(mail);
            datiPersonali.setVia(via);
            datiPersonali.setCivico(civico);
            datiPersonali.setCitta(citta);
            datiPersonali.setCap(Integer.valueOf(cap));
            datiPersonaliService.update(datiPersonali);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean addViaggioPreferito(Viaggio viaggio){
        try {
            ViaggiPreferitiEntity viaggioPreferito = new ViaggiPreferitiEntity();
            ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
            viaggioPreferito = viaggioPreferito.toViaggiPreferitiEntity(viaggio);
            viaggioPreferito.setUsername(account.getUsername());
            viaggiPreferitiService.persist(viaggioPreferito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteViaggioPreferito(ViaggiPreferitiEntity viaggio){
        try {
            ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
            viaggiPreferitiService.deleteById(viaggio.getViaggioPreferitoId().toString());
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public boolean addAcquistoToStorico (Acquisto acquisto) {
        try {
            StoricoAcquistiEntity storicoAcquisti = new StoricoAcquistiEntity();
            StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
            storicoAcquisti = storicoAcquisti.toStoricoAcquistiEntity(acquisto);
            storicoAcquisti.setUsername(account.getUsername());
            storicoAcquistiService.persist(storicoAcquisti);
            return true;
        } catch (Exception e) {
            return false;
        }

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



    public static boolean checkUserPassword(String user, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        var accountUser = new AccountService().findByUsername(user);
        return accountUser != null && CryptographyUtils.encryptPassword(password).equals(accountUser.getPassword());
    }

    public boolean checkExistingUsername(String username){
         if(new AccountService().findByUsername(username) == null || username.length() == 0)
             return false;
         else
             return true;

    }

    public static Account login (String user) {
        Account account = Account.getInstance();
        account.setAccount(user);
        account.setDatiPersonali(user);
        setLoggedIn(true);
        return account;
    }

    public void logout () {
        instance = null;
        setLoggedIn(false);
    }

    public static boolean signUp(String username, String password, String nome, String cognome, LocalDate dataDiNascita, String mail, String via, String civico, String citta, String cap) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        try {
            AccountService accountService = new AccountService();
            DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
            AccountEntity account = new AccountEntity();
            DatiPersonaliEntity dati = new DatiPersonaliEntity();
            account.setUsername(username);
            account.setPassword(CryptographyUtils.encryptPassword(password));
            account.setPuntiFedelta(0);
            accountService.persist(account);
            dati.setUsername(username);
            dati.setNome(nome);
            dati.setCognome(cognome);
            dati.setDataNascita(Date.valueOf(dataDiNascita));
            dati.setMail(mail);
            dati.setVia(via);
            dati.setCivico(civico);
            dati.setCitta(citta);
            dati.setCap(Integer.valueOf(cap));
            datiPersonaliService.persist(dati);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
