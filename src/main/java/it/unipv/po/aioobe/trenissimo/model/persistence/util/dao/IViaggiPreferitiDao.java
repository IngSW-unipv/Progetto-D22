package it.unipv.po.aioobe.trenissimo.model.persistence.util.dao;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

public interface IViaggiPreferitiDao {

    public List<ViaggiPreferitiEntity> findAll();
    public ViaggiPreferitiEntity findById(String id);
    public List<ViaggiPreferitiEntity> findByUsername(String user);
    public void persist(ViaggiPreferitiEntity viaggiPreferiti);
    public void update(ViaggiPreferitiEntity viaggiPreferiti);
    public void delete(ViaggiPreferitiEntity viaggiPreferiti);

}
