package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PrezzoIvaFactory {

    IPrezzoIvaStrategy prezzoIva;
    private final String PROPERTYNAME="prezzoIva.strategy.class.name";

    public IPrezzoIvaStrategy getPrezzoIvaStrategy() {

        if(prezzoIva==null) {

            Properties p = new Properties(System.getProperties());
            String prezzoIvaClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                prezzoIvaClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(prezzoIvaClassName).getConstructor();
                prezzoIva=(IPrezzoIvaStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                prezzoIva=null;
            }

        }

        return prezzoIva;
    }

}
