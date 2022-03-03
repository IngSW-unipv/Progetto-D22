package it.unipv.po.aioobe.trenissimo.model.persistence.util.service;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

public interface IViaggiPreferitiService {

    public List<ViaggiPreferitiEntity> findAll();
    public ViaggiPreferitiEntity findById(String id);
    public List<ViaggiPreferitiEntity> findByAccount(String account);
    public void persist(ViaggiPreferitiEntity viaggiPreferiti);
    public void update(ViaggiPreferitiEntity viaggiPreferiti);
    public void deleteById(String id);

}
