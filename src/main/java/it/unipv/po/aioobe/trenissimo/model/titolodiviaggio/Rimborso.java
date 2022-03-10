package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.VoucherEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.IRimborsoStrategy;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy.RimborsoFactory;

import java.time.LocalDate;

public class Rimborso {

    public StoricoAcquistiEntity storicoAcquisti;
    public TitoloViaggioEntity titoloViaggioEntity;
    public LocalDate dataRichiesta;
    public String id;
    public IRimborsoStrategy rimborsoStrategy;

    public Rimborso(String id) {
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        this.storicoAcquisti = storicoAcquistiService.findByTitoloViaggioId(id);
        this.titoloViaggioEntity = titoloViaggioService.findById(id);
        this.id=id;
        this.dataRichiesta = LocalDate.now();

        RimborsoFactory f=new RimborsoFactory();
        rimborsoStrategy =f.getRimborso();
    }

    public TitoloViaggioEntity getTitoloViaggioEntity() {
        return titoloViaggioEntity;
    }

    public StoricoAcquistiEntity getStoricoAcquisti() {
        return storicoAcquisti;
    }

    public LocalDate getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDate dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }

    public VoucherEntity getRimborso() {
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        titoloViaggioService.deleteById(id);
        return rimborsoStrategy.getPrezzoTot(this);
    }

    public static boolean checkIdBiglietto(String idBiglietto) {
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        TitoloViaggioEntity titolo = titoloViaggioService.findById(idBiglietto);
        return titolo != null;
    }
}
