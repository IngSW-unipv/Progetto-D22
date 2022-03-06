package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public class FiltroCambi implements IFiltro{

    private int minCambi, maxCambi;

    public FiltroCambi(int minCambi, int maxCambi) {
        this.minCambi = minCambi;
        this.maxCambi = maxCambi;
    }

    public int getMinCambi() {
        return minCambi;
    }

    public void setMinCambi(int minCambi) {
        this.minCambi = minCambi;
    }

    public int getMaxCambi() {
        return maxCambi;
    }

    public void setMaxCambi(int maxCambi) {
        this.maxCambi = maxCambi;
    }

    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter( (x) -> x.getNumeroCambi() >= minCambi && x.getNumeroCambi() <= maxCambi).toList();
    }
}
