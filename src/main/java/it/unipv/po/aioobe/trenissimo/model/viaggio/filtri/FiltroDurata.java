package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public class FiltroDurata implements IFiltro{

    private int minDurata, maxDurata;

    public FiltroDurata(int minDurata, int maxDurata){
        this.minDurata = minDurata;
        this.maxDurata = maxDurata;
    }

    public int getMinDurata() {
        return minDurata;
    }

    public void setMinDurata(int minDurata) {
        this.minDurata = minDurata;
    }

    public int getMaxDurata() {
        return maxDurata;
    }

    public void setMaxDurata(int maxDurata) {
        this.maxDurata = maxDurata;
    }

    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter( (x) -> x.getDurata() >= this.minDurata && x.getDurata() <= this.maxDurata).toList();
    }
}
