package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.Voucher;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.IRimborsoStrategy;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.RimborsoFactory;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.RimborsoStrategy;

import java.time.LocalDate;

public class Rimborso {

    public CorsaSingola biglietto;
    public LocalDate dataRichiesta;
    public IRimborsoStrategy rimborsoStrategy;

    public Rimborso(CorsaSingola biglietto) {
        this.biglietto = biglietto;
        this.dataRichiesta = LocalDate.now();

        RimborsoFactory f=new RimborsoFactory();
        rimborsoStrategy =f.getRimborso();
    }

    public CorsaSingola getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(CorsaSingola biglietto) {
        this.biglietto = biglietto;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public Voucher getRimborso() {

        return rimborsoStrategy.getPrezzoTot(this);

    }
}
