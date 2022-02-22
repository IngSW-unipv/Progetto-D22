package it.unipv.po.aioobe.trenissimo.model.viaggio.posizione;

public class Posizione {
    private float latitudine;
    private float longitudine;

    public Posizione(float latitudine, float longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(float latitudine) {
        this.latitudine = latitudine;
    }

    public float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(float longitudine) {
        this.longitudine = longitudine;
    }

    public float getDistanza(Posizione pos){
        float dist;
        dist = (float) Math.sqrt((Math.pow((latitudine - pos.latitudine), 2)) + (Math.pow((longitudine - pos.longitudine), 2)));
        return dist;
    }
}
