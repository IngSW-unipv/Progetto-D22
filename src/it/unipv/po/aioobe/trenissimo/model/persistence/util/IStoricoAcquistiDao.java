package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

public interface IStoricoAcquistiDao {

    public List<StoricoAcquistiEntity> findAll();

}
