package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;

public class Login {

    public static boolean checkUserPassword(String user, String password){

        var accountUser = new AccountService().findByUsername(user);

        return accountUser != null && accountUser.getPassword().equals(password);
    }
}
