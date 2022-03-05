package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;


import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public class FiltroPrezzo implements IFiltro {

    private double minPrezzo, maxPrezzo;

    public FiltroPrezzo(double minPrezzo, double maxPrezzo) {
        this.minPrezzo = minPrezzo;
        this.maxPrezzo = maxPrezzo;
    }

    public double getMinPrezzo() {
        return minPrezzo;
    }

    public void setMinPrezzo(double minPrezzo) {
        this.minPrezzo = minPrezzo;
    }

    public double getMaxPrezzo() {
        return maxPrezzo;
    }

    public void setMaxPrezzo(double maxPrezzo) {
        this.maxPrezzo = maxPrezzo;
    }

    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter(x -> x.getPrezzo() < maxPrezzo && x.getPrezzo() > minPrezzo).toList();
    }
}
