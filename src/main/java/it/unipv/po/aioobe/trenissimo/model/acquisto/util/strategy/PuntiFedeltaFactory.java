package it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class PuntiFedeltaFactory {

    IPuntiFedeltaStrategy puntiFedelta;
    private final String PROPERTYNAME="puntiFedelta.strategy.class.name";

    public IPuntiFedeltaStrategy getPuntiFedelta() {

        if(puntiFedelta==null) {

            Properties p = new Properties(System.getProperties());
            String puntiFedeltaClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                puntiFedeltaClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(puntiFedeltaClassName).getConstructor();
                puntiFedelta=(IPuntiFedeltaStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                puntiFedelta=null;
            }

        }

        return puntiFedelta;
    }

}
