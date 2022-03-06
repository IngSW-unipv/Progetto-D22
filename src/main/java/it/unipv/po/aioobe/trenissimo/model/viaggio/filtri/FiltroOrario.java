package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;


import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.time.LocalTime;
import java.util.List;

public class FiltroOrario implements IFiltro {

    private int minOrario, maxOrario;

    public FiltroOrario(int minOrario, int maxOrario) {
        this.minOrario = minOrario;
        this.maxOrario = maxOrario;
    }

    public int getMinPrezzo() {
        return minOrario;
    }

    public void setMinPrezzo(int minPrezzo) {
        this.minOrario = minPrezzo;
    }

    public int getMaxPrezzo() {
        return maxOrario;
    }

    public void setMaxPrezzo(int maxPrezzo) {
        this.maxOrario = maxPrezzo;
    }

    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter(x -> x.getOrarioPartenza() >= minOrario && x.getOrarioArrivo() <= maxOrario).toList();
    }
}
