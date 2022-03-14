package it.unipv.po.aioobe.trenissimo.model.viaggio.filtri;

import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.Comparator;
import java.util.List;

/**
 * Classe che implementa l'ordinamento secondo alcuni parametri identificati da una enumeration definita internamente alla classe stessa
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class FiltroOrdina implements IFiltro {
    private Criterio criterio;

    /**
     * Metodo per assegnare all'attributo criterio un tipo di ordinamento scelto tra quelli della enumaration
     *
     * @param criterio
     */
    public FiltroOrdina(Criterio criterio) {
        this.criterio = criterio;
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    /**
     * Metodo che ordina in base al valore dell'attributo criterio
     *
     * @param input lista di Viaggio
     * @return lista di Viaggio
     */
    @Override
    public List<Viaggio> esegui(List<Viaggio> input) {

        switch (criterio) {
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

    /**
     * Enumeration che indica i diversi tipi di ordinamento che si possono eseguire
     */
    public enum Criterio {
        DURATA("Durata"),
        PREZZO("Prezzo"),
        CAMBI("Cambi"),
        ORARIO("Orario");

        public final String nome;

        Criterio(String nome) {
            this.nome = nome;
        }
    }

}
