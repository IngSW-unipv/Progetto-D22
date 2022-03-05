package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum DurataAbbonamento {
    SETTIMANALE("Settimanale"),
    MENSILE("Mensile"),
    TRIMESTRALE("Trimestrale"),
    SEMESTRALE("Semestrale"),
    ANNUALE("Annuale");

    public final String label;

    private DurataAbbonamento(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }

}
