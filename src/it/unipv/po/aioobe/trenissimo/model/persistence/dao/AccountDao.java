package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.IAccountDao;

import java.util.List;

public class AccountDao extends HibernateConnection implements IAccountDao {

    public List<AccountEntity> findAll() {
        List<AccountEntity> accountEntities = (List<AccountEntity>) getCurrentSession().createQuery("from AccountEntity ").list();
        return accountEntities;
    }


    public AccountEntity findById(String id) {
        AccountEntity accountEntity = (AccountEntity) getCurrentSession().get(AccountEntity.class, id);
        return accountEntity;
    }


    public void persist(AccountEntity account) {
        getCurrentSession().save(account);
    }

    public void delete(AccountEntity account) {
        getCurrentSession().delete(account);
    }
}
