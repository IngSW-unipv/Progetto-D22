package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio;

import it.unipv.po.aioobe.trenissimo.model.acquisto.Acquisto;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.StoricoAcquistiEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.TitoloViaggioEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.StoricoAcquistiService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.TitoloViaggioService;
import it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration.*;
import it.unipv.po.aioobe.trenissimo.model.user.Account;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;

import java.util.List;

public class CorsaSingola extends Acquisto {
    private TipoTitoloViaggio tipo;
    private double prezzo;
    private String id;
    private Viaggio viaggio;

    public CorsaSingola(TipoTitoloViaggio tipo, Viaggio viaggio) {
        super();
        this.tipo = TipoTitoloViaggio.BIGLIETTOCORSASINGOLA;
        this.tipo = tipo;
        this.id = "CS" + System.nanoTime();
        this.viaggio = viaggio;
        this.prezzo = viaggio.getPrezzoTot();
    }

    //getter
    public Viaggio getViaggio() {
        return viaggio;
    }

    public TipoTitoloViaggio getTipo() {
        return this.tipo;
    }

    @Override
    public double getPrezzo() {
        return this.prezzo;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public void pagare() {
        StoricoAcquistiService storicoAcquistiService = new StoricoAcquistiService();
        StoricoAcquistiEntity storicoAcquistiEntity = new StoricoAcquistiEntity();
        TitoloViaggioService titoloViaggioService = new TitoloViaggioService();
        TitoloViaggioEntity titoloViaggioEntity = new TitoloViaggioEntity();
        titoloViaggioService.persist(titoloViaggioEntity.toTitoloViaggioEntity(this));
        storicoAcquistiEntity = storicoAcquistiEntity.toStoricoAcquistiEntity(this);
        if(Account.getLoggedIn())
            storicoAcquistiEntity.setUsername(Account.getInstance().getUsername());
        else
            storicoAcquistiEntity.setUsername(null);
        storicoAcquistiService.persist(storicoAcquistiEntity);
    }

}

