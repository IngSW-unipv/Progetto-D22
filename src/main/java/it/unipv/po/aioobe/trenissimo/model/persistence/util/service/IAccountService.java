package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 * @author ArrayIndexOutOfBoundsException
 */
public interface IAccountService {

    public List<AccountEntity> findAll();
    public AccountEntity findByUsername(String user);
    public void persist (AccountEntity account);
    public void update(AccountEntity account);
    public void deleteByUsername(String user);


}
