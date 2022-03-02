package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.RoutesEntity;
import java.util.List;

public interface IRoutesService {

    public List<RoutesEntity> findAll();

}
