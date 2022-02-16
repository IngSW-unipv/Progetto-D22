package src.it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import src.it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;

import java.util.UUID;

public interface ITitoloViaggio {

    //getter e setter
    public TipoTitoloViaggio getTipo();
    public DurataTitoloViaggio getDurata();
    public double getPrezzo();
    public UUID getId();


    public void setDurata(DurataTitoloViaggio durata);
    public void setPrezzo(double prezzo);
    public void setId(UUID id);

}
