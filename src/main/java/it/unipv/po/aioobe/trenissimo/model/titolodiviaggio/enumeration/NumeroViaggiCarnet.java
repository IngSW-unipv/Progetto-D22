package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.enumeration;

public enum NumeroViaggiCarnet {
    _5_VIAGGI("5 viaggi"),
    _10_VIAGGI("10 viaggi"),
    _15_VIAGGI("15 viaggi");

    public final String label;

    private NumeroViaggiCarnet(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }

}
