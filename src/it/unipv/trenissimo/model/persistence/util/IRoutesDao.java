package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.RoutesEntity;
import java.util.List;

public interface IRoutesDao {

    public List<RoutesEntity> findAll();

}
