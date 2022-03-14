package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTot;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy istanziare
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoTotFactory {

    IPrezzoTotStrategy prezzoTot;

    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da istanziare
     */
    private final String PROPERTYNAME="prezzoTot.strategy.class.name";

    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME
     */
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
