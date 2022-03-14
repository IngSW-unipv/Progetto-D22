package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;

import java.util.List;

/**
 * Interfaccia che definisce le signature dei metodi che implementeranno gli stessi metodi del Dao con l'aggiunta della gestione della sessione del framework Hibernate
 *
 * @author ArrayIndexOutOfBoundsException
 */
public interface IAccountService {

    List<AccountEntity> findAll();

    AccountEntity findByUsername(String user);

    void persist(AccountEntity account);

    void update(AccountEntity account);

    void deleteByUsername(String user);


}
