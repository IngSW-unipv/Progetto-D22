package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum NumeroViaggiCarnet {
    _5_VIAGGI,
    _10_VIAGGI,
    _15_VIAGGI;

    @Override
    public String toString(){
        return name().substring(1).replace("_", " ").toLowerCase();
    }
}
