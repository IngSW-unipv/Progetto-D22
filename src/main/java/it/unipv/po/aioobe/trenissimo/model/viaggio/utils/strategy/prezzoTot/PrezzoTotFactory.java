package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza.IPrezzoPerDistanzaStrategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PrezzoTotFactory {

    IPrezzoTotStrategy prezzoTot;
    private final String PROPERTYNAME="prezzoTot.strategy.class.name";

    public IPrezzoTotStrategy getPrezzoTot() {

        if(prezzoTot==null) {

            Properties p = new Properties(System.getProperties());
            String prezzoTotClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                prezzoTotClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(prezzoTotClassName).getConstructor();
                prezzoTot=(IPrezzoTotStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                prezzoTot=null;
            }

        }

        return prezzoTot;
    }

}
