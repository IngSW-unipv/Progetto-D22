package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

public interface ITitoloViaggio extends IAcquisto {

    public double getPrezzo();
    public String getId();

}
