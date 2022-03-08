package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PrezzoPerDistanzaFactory {

    IPrezzoPerDistanzaStrategy prezzoPerDistanza;
    private final String PROPERTYNAME="prezzoPerDistanza.strategy.class.name";

    public IPrezzoPerDistanzaStrategy getPrezzoPerDistanzaStrategy() {

        if(prezzoPerDistanza==null) {

            Properties p = new Properties(System.getProperties());
            String prezzoPerDistanzaClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                prezzoPerDistanzaClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(prezzoPerDistanzaClassName).getConstructor();
                prezzoPerDistanza=(IPrezzoPerDistanzaStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                prezzoPerDistanza=null;
            }

        }

        return prezzoPerDistanza;
    }

}
