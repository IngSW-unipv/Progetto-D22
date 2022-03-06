package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;


public class Account {

    private static Account instance;
    private AccountEntity account;
    private DatiPersonaliEntity datiPersonali;

    public static Account getInstance() {
        if (instance == null)
            instance = new Account();
        return instance;
    }

    public void clear() {
        instance = null;
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

    public String getPassword() {
        return this.account.getPassword();
    }

    public void setUsername(String username) {
        this.account.setUsername(username);
    }

    public void setPassword(String password) {
        this.account.setPassword(password);
    }

    public void salvaModificaDati(String nome, String cognome, LocalDate dataNascita, String mail, String via, String civico, String citta, String cap){
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        datiPersonali.setNome(nome);
        datiPersonali.setCognome(cognome);
        datiPersonali.setDataNascita(Date.valueOf(dataNascita));
        datiPersonali.setMail(mail);
        datiPersonali.setVia(via);
        datiPersonali.setCivico(Integer.valueOf(civico));
        datiPersonali.setCitta(citta);
        datiPersonali.setCap(Integer.valueOf(cap));
        datiPersonaliService.update(datiPersonali);
    }

    public void addViaggioPreferito(Viaggio viaggio){
        ViaggiPreferitiEntity viaggioPreferito = new ViaggiPreferitiEntity();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        viaggioPreferito = viaggioPreferito.toViaggiPreferitiEntity(viaggio);
        viaggioPreferito.setUsername(account.getUsername());
        viaggiPreferitiService.persist(viaggioPreferito);
    }

    public void deleteViaggioPreferito(ViaggiPreferitiEntity viaggio){
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        viaggiPreferitiService.deleteById(viaggio.getViaggioPreferitoId().toString());
    }

    public void addAcquistoToStorico (IAcquisto acquisto) {
        StoricoAcquistiEntity storicoAcquisti = new StoricoAcquistiEntity();
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        storicoAcquisti = storicoAcquisti.toStoricoAcquistiEntity(acquisto);
        storicoAcquisti.setUsername(account.getUsername());
        storicoAcquistiService.persist(storicoAcquisti);
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


    public static boolean checkUserPassword(String user, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        var accountUser = new AccountService().findByUsername(user);
        return accountUser != null && CryptographyUtils.encryptPassword(password).equals(accountUser.getPassword());
    }

    public static Account login (String user) {
        Account account = Account.getInstance();
        account.setAccount(user);
        account.setDatiPersonali(user);
        return account;
    }

    public void logout () {
        Account.getInstance().clear();
    }

    public void signUp(String username, String password, String nome, String cognome, String dataDiNascita, String mail, String via, String civico, String citta, String cap) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        AccountService accountService = new AccountService();
        DatiPersonaliService datiPersonaliService = new DatiPersonaliService();
        AccountEntity account = new AccountEntity();
        DatiPersonaliEntity dati = new DatiPersonaliEntity();
        account.setUsername(username);
        account.setPassword(CryptographyUtils.encryptPassword(password));
        dati.setUsername(username);
        dati.setNome(nome);
        dati.setCognome(cognome);
        dati.setDataNascita(java.sql.Date.valueOf(dataDiNascita));
        dati.setMail(mail);
        dati.setVia(via);
        dati.setCivico(Integer.valueOf(civico));
        dati.setCitta(citta);
        dati.setCap(Integer.valueOf(cap));
        accountService.persist(account);
        datiPersonaliService.persist(dati);
    }







}
