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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe singleton che modellizza un account.
 * @author ArrayIndexOutOfBoundsException
 */
public class Account {

    private static Account instance;
    private AccountEntity account;
    private DatiPersonaliEntity datiPersonali;


    public static SimpleBooleanProperty loggedInProperty = new SimpleBooleanProperty(false);

    /**
     * Metodo utilizzato per conoscere lo stato del login.
     * @return "true" se l'utente risulta loggato. <br>
     * "false" altrimenti.
     */
    public static boolean getLoggedIn() {
        return loggedInProperty.get();
    }

    /**
     * Metodo per settare lo stato del login
     * @param loggedIn
     */
    public static void setLoggedIn(boolean loggedIn) {
        loggedInProperty.set(loggedIn);
    }

    /**
     * Metodo per ottenere l'istanza di account.
     * @return un'istanza di account.
     */
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

    public void setUsername(String username) {
        this.account.setUsername(username);
    }

    /**
     * Metodo che si occupa di prendere una "password" in chiaro come parametro (solitamente derivante da una TextField),
     * la cripta e la aggiorna sul database.
     * @param password
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @see CryptographyUtils
     */
    public void setPassword(String password) throws NoSuchPaddingException, IOException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        this.account.setPassword(CryptographyUtils.encryptPassword(password));
        AccountService accountService = new AccountService();
        accountService.update(this.account);
    }

    public String getPuntiFedelta(){
        return this.account.getPuntiFedelta().toString();
    }

    /**
     * Metodo che si occupa di prendere tutti i parametri (Dati personali) che gli vengono forniti e li aggiorna nel database.
     * @param nome
     * @param cognome
     * @param dataNascita
     * @param mail
     * @param via
     * @param civico
     * @param citta
     * @param cap
     * @return "true" se il salvataggio dei dati personali è andato a buon fine. <br>
     * "false" altrimenti.
     */
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

    /**
     * Metodo che si occupa di prendere un Viaggio come parametro, e salvarlo come viaggio preferito all'interno del database.
     * @param viaggio
     * @return "true" se l'aggiunta del viaggio preferito è andata a buon fine. <br>
     * "false" altrimenti.
     */
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

    /**
     * Metodo che si occupa di prendere un ViaggioPreferitoEntity come parametro e di eliminarlo dal database.
     * @param viaggio ViaggioPreferitoEntity.
     * @return "true" se l'eliminazione del viaggio preferito è andata a buon fine. <br>
     * "false" altrimenti.
     */
    public boolean deleteViaggioPreferito(ViaggiPreferitiEntity viaggio){
        try {
            ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
            viaggiPreferitiService.deleteById(viaggio.getViaggioPreferitoId().toString());
            return true;
        } catch (Exception e){
            return false;
        }

    }

    /**
     * Metodo che si occupa di prendere un acquisto come parametro e salvarlo nel database.
     * @param acquisto
     * @return "true" se l'aggiunta dell'acquisto al database, è andata a buon fine. <br>
     * "false" altrimenti.
     */
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

    /**
     * Metodo per controllare che il CAP sia di 5 cifre e che siano soltanto numeri.
     * @param CAP
     * @return "true" se il CAP ha 5 cifre e se è composto da soltanto numeri. <br>
     * "false" altrimenti.
     */
    public boolean checkCAP(String CAP){
        return CAP.length() == 5 && CAP.matches("^[0-9]+$");
    }

    /**
     * Metodo per controllare che la mail inserita segua lo standard previsto.
     * @param email
     * @return "true" se la mail rispetta lo standard. <br>
     * "false" altrimenti.
     */
    public boolean checkEmail(String email){

        return email.matches("[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}");
    }

    /**
     * Metodo utilizzato per verificare la validità della data di nascita.
     * @param data
     * @return "true" se la data passata come parametro, è precedente a quella attuale.
     * "false" altrimenti.
     */
    public boolean checkDataNascita(LocalDate data){
        return data.isBefore(LocalDate.now());
    }

    /**
     * Metodo utilizzato per verificare che la "password" inserita come parametro, corrisponda a quella presente nel database.
     * @param user
     * @param password
     * @return "true" se la password dell'user, inserita come parametro, corrisponde a quella nel database. <br>
     * "false" altrimenti.
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
    public static boolean checkUserPassword(String user, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException {
        var accountUser = new AccountService().findByUsername(user);
        return accountUser != null && CryptographyUtils.encryptPassword(password).equals(accountUser.getPassword());
    }

    /**
     * Metodo utilizzato per verificare se l'username inserito come parametro, esiste già nel database.
     * @param username
     * @return "true" se viene trovata una corrispondenza.
     * "false" altrimenti.
     */
    public boolean checkExistingUsername(String username){
         if(new AccountService().findByUsername(username) == null || username.length() == 0)
             return false;
         else
             return true;

    }

    /**
     * Metodo utilizzato per effettuare il login con l'username inserito come parametro.
     * @param user
     * @return Account
     */
    public static Account login (String user) {
        Account account = Account.getInstance();
        account.setAccount(user);
        account.setDatiPersonali(user);
        setLoggedIn(true);
        return account;
    }

    /**
     * Metodo utilizzato per effettuare il logout
     */
    public void logout () {
        instance = null;
        setLoggedIn(false);
    }

    /**
     * Metodo utilizzato per fare la registrazione di un nuovo utente. Tutti i parametri che vengono passati al metodo, vengono salvati nel database.
     * @param username
     * @param password
     * @param nome
     * @param cognome
     * @param dataDiNascita
     * @param mail
     * @param via
     * @param civico
     * @param citta
     * @param cap
     * @return "true" se la registrazione è andata a buon fine. <br>
     * "false" altrimenti.
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     */
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
