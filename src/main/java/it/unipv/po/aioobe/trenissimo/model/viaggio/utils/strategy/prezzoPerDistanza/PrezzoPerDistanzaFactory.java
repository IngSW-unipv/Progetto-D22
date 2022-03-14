package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoPerDistanza;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy istanziare
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoPerDistanzaFactory {

    IPrezzoPerDistanzaStrategy prezzoPerDistanza;
    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da istanziare
     */
    private final String PROPERTYNAME="prezzoPerDistanza.strategy.class.name";
    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME
     */
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
