package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot.IPrezzoTotStrategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class RimborsoFactory {

    IRimborsoStrategy rimborso;
    private final String PROPERTYNAME="rimborso.strategy.class.name";

    public IRimborsoStrategy getRimborso() {

        if(rimborso==null) {

            Properties p = new Properties(System.getProperties());
            String rimborsoClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                rimborsoClassName=p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(rimborsoClassName).getConstructor();
                rimborso=(IRimborsoStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                rimborso=null;
            }

        }

        return rimborso;
    }

}
