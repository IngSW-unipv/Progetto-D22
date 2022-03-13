package it.unipv.po.aioobe.trenissimo.model.persistence.util.exception;

import org.hibernate.HibernateException;

/**
 * Eccezione che viene lanciata in caso di errori inerenti al framework Hibernate e al colloquio con il database
 */
public class ConnectionDBException extends HibernateException {

    public ConnectionDBException(String message) {
        super(message);
    }
}
