package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoIva;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy istanziare
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoIvaFactory {

    IPrezzoIvaStrategy prezzoIva;
    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da istanziare
     */
    private final String PROPERTYNAME="prezzoIva.strategy.class.name";
    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME
     */
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
