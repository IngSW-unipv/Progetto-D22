package it.unipv.trenissimo.model.persistence.util;

import it.unipv.trenissimo.model.persistence.entity.StopsEntity;
import java.util.List;

public interface IStopsService {

    public List<StopsEntity> findAll();

}
