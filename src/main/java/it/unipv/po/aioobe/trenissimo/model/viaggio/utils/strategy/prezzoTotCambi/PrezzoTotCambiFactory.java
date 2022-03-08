package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza.IPrezzoPerDistanzaStrategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PrezzoTotCambiFactory {

    IPrezzoTotCambiStrategy prezzoTotCambi;
    private final String PROPERTYNAME="prezzoTotCambi.strategy.class.name";

    public IPrezzoTotCambiStrategy getPrezzoTotCambiStrategy() {

        if(prezzoTotCambi==null) {

            Properties p = new Properties(System.getProperties());
            String PrezzoTotCambiClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                PrezzoTotCambiClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(PrezzoTotCambiClassName).getConstructor();
                prezzoTotCambi=(IPrezzoTotCambiStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                prezzoTotCambi=null;
            }

        }

        return prezzoTotCambi;
    }

}
