package it.unipv.po.aioobe.trenissimo.model.acquisto;

import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.IPuntiFedeltaStrategy;
import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.PuntiFedeltaFactory;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe astratta che individua un acquisto
 * @author ArrayIndexOutOfBoundsException
 */

public abstract class Acquisto {

    private double prezzoTot;
    private final LocalDateTime dataAcquisto;
    private IPuntiFedeltaStrategy puntiFedeltaStrategy;

    /**
     * Costruttore che istanzia il Factory per i punti fedeltà ed assegna l'istanza della Strategy all'attributo privato puntiFedeltaStrategy.
     * Inoltre assegna al prezzo un valore iniziale di 0 ed assegna alla data di acquisto la data attuale al momento della chiamata del costruttore.
     */
    public Acquisto(){
        PuntiFedeltaFactory f=new PuntiFedeltaFactory();
        puntiFedeltaStrategy =f.getPuntiFedelta();
        this.prezzoTot = 0;
        this.dataAcquisto = LocalDateTime.now();
    }

    abstract public String getId();

    public abstract double getPrezzo();

    public abstract void setPrezzo(double prezzo);

    public double getPrezzoTot() {
        return prezzoTot;
    }

    /**
     * Metodo che calcola il valore totale della lista di acquisti passata come parametro
     * @param acquisti una lista di istanze della classe Acquisto
     */
    public void setPrezzoTot(@NotNull List<Acquisto> acquisti) {
        double prezzoTot = 0;
        for (Acquisto a: acquisti) {
            prezzoTot = prezzoTot + a.getPrezzo();
        }
        this.prezzoTot = prezzoTot;
    }

    /**
     * Metodo che richiama il metodo setPuntiFedelta della PuntiFedeltaStrategy
     * @param acquisti una lista di istanze della classe Acquisto
     */
    public void puntiFedelta(List<Acquisto> acquisti){
        this.setPrezzoTot(acquisti);
        puntiFedeltaStrategy.setPuntiFedelta(this);
    }

    /**
     * Metodo astratto che verrà implementato dalle classi che estenderanno questa classe
     */
    public abstract boolean pagare();

}
