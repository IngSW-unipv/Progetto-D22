package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.ViaggiPreferitiService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioPreferito;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.ArrayList;

public class Account {

    private static Account instance;
    private AccountEntity account;
    private DatiPersonaliEntity datiPersonali;
    private ArrayList<ViaggiPreferitiEntity> viaggioPreferito;
    private ArrayList<StoricoAcquistiEntity> storicoAcquisti;

    //getter
    public static Account getInstance(AccountEntity account, DatiPersonaliEntity datiPersonali) {
        if (instance == null)
            instance = new Account(account, datiPersonali);
        return instance;
    }

    private Account(AccountEntity account, DatiPersonaliEntity datiPersonali){

        this.account = account;
        this.datiPersonali = datiPersonali;
        this.viaggioPreferito = new ArrayList<>();
        this.storicoAcquisti = new ArrayList<>();
    }

    public DatiPersonaliEntity getDatiPersonali() {
        return this.datiPersonali;
    }

    public String getUsername() {
        return this.account.getUsername();
    }

    public String getPasswordHash() {
        return this.account.getPassword();
    }

    public Integer getId() {
        return this.account.getAccountId();
    }

    public ArrayList<ViaggiPreferitiEntity> getViaggiPreferiti() {
        return this.viaggioPreferito;
    }

    public ArrayList<StoricoAcquistiEntity> getStoricoAcquisti() {
        return this.storicoAcquisti;
    }

    //setter
    public void setDatiPersonali(DatiPersonaliEntity datiPersonali) {
        this.datiPersonali = datiPersonali;
    }

    public void setPassword(String password) {
        this.account.setPassword(password);
    }

    public void addViaggioPreferito(Viaggio viaggio){
        ViaggiPreferitiEntity viaggioPreferito = new ViaggiPreferitiEntity();
        ViaggiPreferitiService viaggiPreferitiService = new ViaggiPreferitiService();
        viaggioPreferito = viaggioPreferito.toViaggiPreferitiEntity(viaggio);
        viaggioPreferito.setAccountId(account.getAccountId());
        this.viaggioPreferito.add(viaggioPreferito);
        viaggiPreferitiService.persist(viaggioPreferito);
    }


    public void deleteViaggioPreferito(ViaggioPreferito viaggio){
        this.viaggioPreferito.remove(viaggio);
    }


    public void storicoAcquisti(IAcquisto acquisto){
        // todo
    }


}
