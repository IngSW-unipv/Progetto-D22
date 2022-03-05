package it.unipv.po.aioobe.trenissimo.model.user;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.AccountService;

public class Login {

    private static Login instance;

    public static Login getInstance() {
        if (instance == null)
            instance = new Login();
        return instance;
    }

    public static boolean checkUserPassword(String user, String password){
        var accountUser = new AccountService().findByUsername(user);
        return accountUser != null && accountUser.getPassword().equals(password);
    }

    public void login (String user) {
        Account account = Account.getInstance();
        account.setAccount(user);
        account.setDatiPersonali(user);
    }

}
