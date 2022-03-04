package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.acquisto.IAcquisto;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;

public interface ITitoloViaggio extends IAcquisto {

    //getter e setter
    public TipoTitoloViaggio getTipo();
    public DurataTitoloViaggio getDurata();
    public double getPrezzo();
    public String getId();


    public void setDurata(DurataTitoloViaggio durata);
    public void setPrezzo(double prezzo);

}
