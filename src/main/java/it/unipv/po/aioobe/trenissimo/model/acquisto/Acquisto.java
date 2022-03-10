package it.unipv.po.aioobe.trenissimo.model.acquisto;

import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.IPuntiFedeltaStrategy;
import it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy.PuntiFedeltaFactory;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.RimborsoFactory;
import it.unipv.po.aioobe.trenissimo.model.user.Account;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Acquisto {

    private List<IAcquisto> acquisti;
    private double prezzoTot;
    private LocalDateTime dataAcquisto;
    private IPuntiFedeltaStrategy puntiFedeltaStrategy;

    public Acquisto() {
        this.acquisti = new ArrayList<>();
        this.prezzoTot = 0;
        this.dataAcquisto = LocalDateTime.now();

        PuntiFedeltaFactory f=new PuntiFedeltaFactory();
        puntiFedeltaStrategy =f.getPuntiFedelta();
    }

    public List<IAcquisto> getAcquisti() {
        return acquisti;
    }

    public double getPrezzoTot() {
        return prezzoTot;
    }

    public void setPrezzoTot(double prezzoTot) {
        this.prezzoTot = prezzoTot;
    }

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void acquistare(List<IAcquisto> carrello) {
        double prezzo = 0;
        this.getAcquisti().addAll(carrello);
        for (IAcquisto a: carrello) {
            prezzo = prezzo + a.getPrezzo();
        }
        this.setPrezzoTot(prezzo);
        puntiFedeltaStrategy.setPuntiFedelta(this);
    }

}
