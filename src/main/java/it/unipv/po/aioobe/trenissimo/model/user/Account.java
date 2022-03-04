package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.DatiPersonaliService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioPreferito;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

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

    public void addViaggioPreferito(Viaggio viaggio){
        ViaggiPreferitiEntity viaggioPreferito = new ViaggiPreferitiEntity();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        viaggioPreferito = viaggioPreferito.toViaggiPreferitiEntity(viaggio);
        viaggioPreferito.setUsername(account.getUsername());
        viaggiPreferitiService.persist(viaggioPreferito);
    }



    public void deleteViaggioPreferito(ViaggioPreferito viaggio){

    }

    //TODO IMPLEMENTARE DELETEVIAGGIOPREFERITO
    //TODO IMPLEMENTARE ADDSTORICOACQUISTO
    //(TODO IMPLEMENTARE MODIFICADATIPERSONALI UNICO CON UPDATE ALL'INTERNO FANNO GLI ALTRI)

}
