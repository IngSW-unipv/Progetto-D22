package it.unipv.po.aioobe.trenissimo.model.viaggio.utils.strategy.prezzoTotCambi;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy istanziare
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class PrezzoTotCambiFactory {

    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da istanziare
     */
    private final String PROPERTYNAME = "prezzoTotCambi.strategy.class.name";
    IPrezzoTotCambiStrategy prezzoTotCambi;

    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME
     */
    public IPrezzoTotCambiStrategy getPrezzoTotCambiStrategy() {

        if (prezzoTotCambi == null) {

            Properties p = new Properties(System.getProperties());
            String PrezzoTotCambiClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                PrezzoTotCambiClassName = p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(PrezzoTotCambiClassName).getConstructor();
                prezzoTotCambi = (IPrezzoTotCambiStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                prezzoTotCambi = null;
            }

        }

        return prezzoTotCambi;
    }

}
