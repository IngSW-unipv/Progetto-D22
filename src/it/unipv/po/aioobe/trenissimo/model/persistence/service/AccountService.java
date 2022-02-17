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
        accountDao.openCurrentSession();
        List<AccountEntity> accounts = accountDao.findAll();
        accountDao.closeCurrentSession();
        return accounts;
    }

    public AccountEntity findById(String id) {
        accountDao.openCurrentSession();
        AccountEntity account = accountDao.findById(id);
        accountDao.closeCurrentSession();
        return account;
    }

    public void persist(AccountEntity account) {
        accountDao.openCurrentSessionwithTransaction();
        accountDao.persist(account);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public void update(AccountEntity account) {
        accountDao.openCurrentSessionwithTransaction();
        accountDao.update(account);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public void deleteById(String id) {
        accountDao.openCurrentSessionwithTransaction();
        AccountEntity account = accountDao.findById(id);
        accountDao.delete(account);
        accountDao.closeCurrentSessionwithTransaction();
    }

    public AccountDao accountDao() {
        return accountDao;
    }
}
