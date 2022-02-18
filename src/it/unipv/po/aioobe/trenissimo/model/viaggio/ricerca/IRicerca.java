package src.it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import src.it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.ArrayList;
import java.util.Comparator;

public interface IRicerca extends Comparable<Viaggio> {
    public ArrayList<Viaggio> eseguiRicerca(IRicerca ricerca);
    public int compareTo(Viaggio viaggio);
    public void ordinaByPrezzo();
    public void ordinaByDurataViaggio(Comparator<Viaggio> comparator);


}
