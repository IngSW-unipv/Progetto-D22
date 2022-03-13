package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;


import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

/**
 * Classe che implementa il filtro per orario massimo e minimo
 * @author ArrayIndexOutOfBoundsException
 */
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
    /**
     * Metodo che filtra per orario massimo e minimo la lista di viaggi presa come parametro
     * @param input lista di Viaggio
     * @return lista di Viaggio
     */
    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter(x -> x.getOrarioPartenza() >= minOrario && x.getOrarioArrivo() <= maxOrario).toList();
    }
}
