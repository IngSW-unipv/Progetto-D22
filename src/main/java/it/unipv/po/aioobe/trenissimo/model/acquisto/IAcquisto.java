package it.unipv.po.aioobe.trenissimo.model.acquisto;

import java.util.List;

public interface IAcquisto {

    public String getId();
    public double getPrezzo();
    public void setPrezzo(double prezzo);
    public void pagare();

}
