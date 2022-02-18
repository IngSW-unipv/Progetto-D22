package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAccountDao;
import java.util.List;

public class AccountDao implements IAccountDao {

    private HibernateConnection conn;

    public AccountDao() {
        this.conn = new HibernateConnection();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<AccountEntity> findAll() {
        List<AccountEntity> accountEntities = (List<AccountEntity>) conn.getCurrentSession().createQuery("from AccountEntity ").list();
        return accountEntities;
    }


    public AccountEntity findById(String id) {
        AccountEntity accountEntity = (AccountEntity) conn.getCurrentSession().get(AccountEntity.class, Integer.parseInt(id));
        return accountEntity;
    }


    public void persist(AccountEntity account) {
        conn.getCurrentSession().save(account);
    }


    public void update(AccountEntity account) {
        conn.getCurrentSession().update(account);
    }

    public void delete(AccountEntity account) {
        conn.getCurrentSession().delete(account);
    }
}
