package it.unipv.po.aioobe.trenissimo.model.persistence.util.exception;

import org.hibernate.HibernateException;

public class ConnectionDBException extends HibernateException {

    public ConnectionDBException(String message) {
        super(message);
    }
}
