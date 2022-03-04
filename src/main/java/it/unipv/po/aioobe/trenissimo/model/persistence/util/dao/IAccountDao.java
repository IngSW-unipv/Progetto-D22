package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.util.List;

public interface IAccountDao {

    public List<AccountEntity> findAll();
    public AccountEntity findByUsername(String user);
    public void persist(AccountEntity account);
    public void update(AccountEntity account);
    public void delete(AccountEntity account);

}
