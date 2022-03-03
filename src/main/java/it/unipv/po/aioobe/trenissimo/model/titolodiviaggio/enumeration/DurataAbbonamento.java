package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum DurataAbbonamento {
    SETTIMANALE,
    MENSILE,
    TRIMESTRALE,
    SEMESTRALE,
    ANNUALE;

    @Override
    public String toString(){
        return name().substring(0,1) + name().substring(1).toLowerCase();
    }
}
