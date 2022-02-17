package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAccountDao;
import java.util.List;

public class AccountDao extends HibernateConnection implements IAccountDao {

    @SuppressWarnings("unchecked")
    public List<AccountEntity> findAll() {
        List<AccountEntity> accountEntities = (List<AccountEntity>) getCurrentSession().createQuery("from AccountEntity ").list();
        return accountEntities;
    }


    public AccountEntity findById(String id) {
        AccountEntity accountEntity = (AccountEntity) getCurrentSession().get(AccountEntity.class, Integer.parseInt(id));
        return accountEntity;
    }


    public void persist(AccountEntity account) {
        getCurrentSession().save(account);
    }


    public void update(AccountEntity account) {
        getCurrentSession().update(account);
    }

    public void delete(AccountEntity account) {
        getCurrentSession().delete(account);
    }
}
