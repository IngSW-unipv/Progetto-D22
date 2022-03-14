package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

/**
 * Classe che implementa il filtro per durata massima e minima
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class FiltroDurata implements IFiltro {

    private int minDurata, maxDurata;

    public FiltroDurata(int minDurata, int maxDurata) {
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

    /**
     * Metodo che filtra per durata massima e minima la lista di viaggi presa come parametro
     *
     * @param input lista di Viaggio
     * @return lista di Viaggio
     */
    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {
        return input.stream().filter((x) -> x.getDurata() >= this.minDurata && x.getDurata() <= this.maxDurata).toList();
    }
}
