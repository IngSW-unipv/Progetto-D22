package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;

import java.util.List;

public interface IStoricoAcquistiService {

    public List<StoricoAcquistiEntity> findAll();

}
