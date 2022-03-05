package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

public interface ITitoloViaggio extends IAcquisto {

    //getter e setter
    public TipoTitoloViaggio getTipo();
    public DurataAbbonamento getDurata();
    public double getPrezzo();
    public String getId();


    public void setDurata(DurataAbbonamento durata);
    public void setPrezzo(double prezzo);

}
