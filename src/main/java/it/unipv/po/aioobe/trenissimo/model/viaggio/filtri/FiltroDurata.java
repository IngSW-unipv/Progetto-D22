package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public class FiltroDurata implements IFiltro{

    private int maxDurata;

    public FiltroDurata(int maxDurata){
        this.maxDurata = maxDurata;
    }

    public int getMaxDurata() {
        return maxDurata;
    }

    public void setMaxDurata(int maxDurata) {
        this.maxDurata = maxDurata;
    }


    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter( (x) -> x.getDurata() <= this.maxDurata).toList();
    }
}
