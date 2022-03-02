package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

public interface IStoricoAcquistiService {

    public List<StoricoAcquistiEntity> findAll();
    public StoricoAcquistiEntity findById(String id);
    public void persist(StoricoAcquistiEntity storicoAcquisti);
    public void update(StoricoAcquistiEntity storicoAcquisti);
    public void deleteById(String id);
}
