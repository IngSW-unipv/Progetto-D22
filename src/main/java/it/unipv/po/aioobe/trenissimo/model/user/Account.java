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










}
