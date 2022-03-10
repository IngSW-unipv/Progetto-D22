package it.unipv.po.aioobe.trenissimo.model.acquisto;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.user.Account;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Acquisto {

    private List<IAcquisto> acquisti;
    private double prezzoTot;
    private LocalDateTime dataAcquisto;

    public Acquisto() {
        this.acquisti = new ArrayList<>();
        this.prezzoTot = 0;
        this.dataAcquisto = LocalDateTime.now();
    }

    public List<IAcquisto> getAcquisti() {
        return acquisti;
    }

    public double getPrezzoTot() {
        return prezzoTot;
    }

    public void setPrezzoTot() {
        for (IAcquisto acquisto: acquisti) {
            prezzoTot = prezzoTot + acquisto.getPrezzo();
        }
    }

    public LocalDateTime getDataAcquisto() {
        return dataAcquisto;
    }

    public void acquistare(List<IAcquisto> carrello) {
        this.getAcquisti().addAll(carrello);
    }

}
