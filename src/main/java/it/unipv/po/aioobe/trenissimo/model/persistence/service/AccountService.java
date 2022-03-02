package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.AccountDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IAccountService;
import java.util.List;

public class AccountService implements IAccountService {

    private static AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public List<AccountEntity> findAll() {
        accountDao.getConn().openCurrentSession();
        List<AccountEntity> accounts = accountDao.findAll();
        accountDao.getConn().closeCurrentSession();
        return accounts;
    }

    public AccountEntity findById(String id) {
        accountDao.getConn().openCurrentSession();
        AccountEntity account = accountDao.findById(id);
        accountDao.getConn().closeCurrentSession();
        return account;
    }

    public void persist(AccountEntity account) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        accountDao.persist(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void update(AccountEntity account) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        accountDao.update(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        AccountEntity account = accountDao.findById(id);
        accountDao.delete(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }
}
