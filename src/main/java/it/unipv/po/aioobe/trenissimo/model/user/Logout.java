package it.unipv.po.aioobe.trenissimo.model.user;

public class Logout {

    private static Logout instance;

    public static Logout getInstance() {
        if (instance == null)
            instance = new Logout();
        return instance;
    }

    public void logout () {
        Account.getInstance().clear();
    }

}
