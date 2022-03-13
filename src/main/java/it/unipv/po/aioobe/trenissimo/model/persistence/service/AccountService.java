package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.dao.AccountDao;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.service.IAccountService;

import java.util.List;

/**
 * Classe che, secondo il pattern Facade, implementa gli stessi metodi di AccountDao con l'aggiunta della gestione delle sessioni del framework Hibernate.
 * Classe progettata per nascondere al modello delle classi la complessità del sistema sottostante (Hibernate)
 * @author ArrayIndexOutOfBoundsException
 */
public class AccountService implements IAccountService {

    private static AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    @Override
    public List<AccountEntity> findAll() {
        accountDao.getConn().openCurrentSession();
        List<AccountEntity> accounts = accountDao.findAll();
        accountDao.getConn().closeCurrentSession();
        return accounts;
    }

    @Override
    public AccountEntity findByUsername(String user) {
        accountDao.getConn().openCurrentSession();
        AccountEntity account = accountDao.findByUsername(user);
        accountDao.getConn().closeCurrentSession();
        return account;
    }

    @Override
    public void persist(AccountEntity account) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        accountDao.persist(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();

    }

    @Override
    public void update(AccountEntity account) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        accountDao.update(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();
    }

    @Override
    public void deleteByUsername(String user) {
        accountDao.getConn().openCurrentSessionwithTransaction();
        AccountEntity account = accountDao.findByUsername(user);
        accountDao.delete(account);
        accountDao.getConn().closeCurrentSessionwithTransaction();
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }
}
