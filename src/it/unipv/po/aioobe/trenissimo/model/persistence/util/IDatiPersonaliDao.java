package it.unipv.po.aioobe.trenissimo.model.persistence.util;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;

import java.util.List;

public interface IDatiPersonaliDao {

    public List<DatiPersonaliEntity> findAll();
    public DatiPersonaliEntity findById(String id);
    public void persist(DatiPersonaliEntity datiPersonali);
    public void update(DatiPersonaliEntity datiPersonali);
    public void delete(DatiPersonaliEntity datiPersonali);

}
