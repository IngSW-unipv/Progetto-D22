package it.unipv.po.aioobe.trenissimo.model.persistence.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IAccountService;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.ICached;

import java.util.List;

public class CachedAccountEntity implements IAccountService, ICached {
    private static CachedAccountEntity instance = null;
    private static AccountService accountService;

    private CachedAccountEntity() {
        accountService = new AccountService();
    }

    public static CachedAccountEntity getInstance() {
        if (instance == null) {
            instance = new CachedAccountEntity();
        }
        return instance;
    }

    private List<AccountEntity> cachedFindAll = null;

    public List<AccountEntity> findAll() {
        if (cachedFindAll == null) {
            cachedFindAll = accountService.findAll();
        }
        return cachedFindAll;
    }

    @Override
    public void clearCache() {
        cachedFindAll = null;
    }
}
