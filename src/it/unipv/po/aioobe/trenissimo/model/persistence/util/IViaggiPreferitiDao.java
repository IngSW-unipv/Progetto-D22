package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.ViaggiPreferitiEntity;

import java.util.List;

public interface IViaggiPreferitiDao {

    public List<ViaggiPreferitiEntity> findAll();
    public ViaggiPreferitiEntity findById(String id);
    public void persist(ViaggiPreferitiEntity viaggiPreferiti);
    public void update(ViaggiPreferitiEntity viaggiPreferiti);
    public void delete(ViaggiPreferitiEntity viaggiPreferiti);

}
