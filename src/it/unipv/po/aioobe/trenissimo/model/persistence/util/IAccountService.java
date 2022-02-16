package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.util.List;

public interface IAccountService {

    public List<AccountEntity> findAll();
    public AccountEntity findById(String id);
    public void persist(AccountEntity account);
    public void deleteById(String id);

}
