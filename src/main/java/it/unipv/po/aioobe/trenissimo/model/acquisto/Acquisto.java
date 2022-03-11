package it.unipv.po.aioobe.trenissimo.model.acquisto;

import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.IPuntiFedeltaStrategy;
import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.PuntiFedeltaFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acquisto {

    private double prezzoTot;
    private final LocalDateTime dataAcquisto;
    private IPuntiFedeltaStrategy puntiFedeltaStrategy;

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

    public void setPrezzoTot(List<Acquisto> acquisti) {
        double prezzoTot = 0;
        for (Acquisto a: acquisti) {
            prezzoTot = prezzoTot + a.getPrezzo();
        }
        this.prezzoTot = prezzoTot;
    }

    public void puntiFedelta(List<Acquisto> acquisti){
        this.setPrezzoTot(acquisti);
        puntiFedeltaStrategy.setPuntiFedelta(this);
    }

    public abstract void pagare();

}
