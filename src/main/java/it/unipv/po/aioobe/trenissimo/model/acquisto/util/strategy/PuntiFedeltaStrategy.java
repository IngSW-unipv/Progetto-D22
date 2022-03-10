package it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;

public class PuntiFedeltaStrategy implements IPuntiFedeltaStrategy{

    @Override
    public void setPuntiFedelta(Acquisto acquisto) {
        AccountService accountService = new AccountService();
        AccountEntity account = new AccountEntity();
        account = accountService.findByUsername(Account.getInstance().getUsername());
        Double prezzo = acquisto.getPrezzoTot();
        if (prezzo<15.00)
            account.setPuntiFedelta(account.getPuntiFedelta()+1*prezzo.intValue());
        else if (prezzo>=15 && prezzo<50)
            account.setPuntiFedelta(account.getPuntiFedelta()+2*prezzo.intValue());
        else if (prezzo>=50 && prezzo<100)
            account.setPuntiFedelta(account.getPuntiFedelta()+3*prezzo.intValue());
        else
            account.setPuntiFedelta(account.getPuntiFedelta()+5*prezzo.intValue());
        accountService.update(account);
    }
}
