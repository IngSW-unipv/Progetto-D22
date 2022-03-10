package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Comparator;
import java.util.List;

public class FiltroOrdina implements IFiltro {

    public enum Criterio{
        DURATA("Durata"),
        PREZZO("Prezzo"),
        CAMBI("Cambi"),
        ORARIO("Orario");

        public final String nome;

        Criterio(String nome) {
            this.nome = nome;
        }
    }

    private Criterio criterio;

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public FiltroOrdina(Criterio criterio) {
        this.criterio = criterio;
    }

    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {

        switch (criterio){
            case DURATA -> {
                return input.stream().sorted(Comparator.comparing(Viaggio::getDurata)).toList();
            }
            case CAMBI -> {
                return input.stream().sorted(Comparator.comparing(Viaggio::getNumeroCambi)).toList();
            }
            case ORARIO -> {
                return input.stream().sorted(Comparator.comparing(Viaggio::getOrarioPartenza)).toList();
            }
            case PREZZO -> {
                return input.stream().sorted(Comparator.comparing(Viaggio::getPrezzoTot)).toList();
            }
            default -> {
                return input;
            }
        }
    }

}
