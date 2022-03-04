package it.unipv.po.aioobe.trenissimo.model.persistence.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.HibernateConnection;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.util.dao.IAccountDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccountDao implements IAccountDao {

    private HibernateConnection conn;

    public AccountDao() {
        this.conn = HibernateConnection.getInstance();
    }

    public HibernateConnection getConn() {
        return conn;
    }

    public void setConn(HibernateConnection conn) {
        this.conn = conn;
    }

    @SuppressWarnings("unchecked")
    public List<AccountEntity> findAll(){
        List<AccountEntity> accountEntities = (List<AccountEntity>) conn.getCurrentSession().createQuery("from AccountEntity ").list();
        return accountEntities;
    }


    public AccountEntity findByUsername(String user) {
        AccountEntity accountEntity = (AccountEntity) conn.getCurrentSession().get(AccountEntity.class, user);
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
