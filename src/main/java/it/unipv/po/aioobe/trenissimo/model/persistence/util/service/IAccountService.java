package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.sql.SQLException;
import java.util.List;

public interface IAccountService {

    public List<AccountEntity> findAll();
    public AccountEntity findByUsername(String user);
    public void persist (AccountEntity account) throws SQLException;
    public void update(AccountEntity account);
    public void deleteByUsername(String user) throws SQLException;


}
