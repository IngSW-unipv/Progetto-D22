package it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import org.jetbrains.annotations.NotNull;

/**
 * Classe che implementa una strategia per l'assegnazione dei punti fedeltà
 * @author ArrayIndexOutOfBoundsException
 */

public class PuntiFedeltaStrategy implements IPuntiFedeltaStrategy{

    /**
     * Override del metodo la cui signature è stata ereditata dall'interfaccia implementata.
     * Metodo che calcola il numero di punti fedeltà da aggiungere all'account con cui è stato fatto l'acquisto e aggiorna il database con tale dato.
     * L'assegnamento dei punti fedeltà viene fatto secondo la seguente strategia: 1 punto ogni 1€ di spesa per acquisti sotto i 15€; 2 punti ogni 1€ di spesa per acquisti fatti tra i 15€ e i 50€; 3 punti ogni 1€ di spesa per acquisti fatti tra i 50€ e i 100€; 5 punti ogni 1€ di spesa per acquisti fatti sopra i 100€
     * @param acquisto
     */
    @Override
    public void setPuntiFedelta(@NotNull Acquisto acquisto) {
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
