package it.unipv.po.aioobe.trenissimo.model.titolodiviaggio.utils.strategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy instanziare.
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class RimborsoFactory {

    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da instanziare.
     */
    private final String PROPERTYNAME = "rimborso.strategy.class.name";
    IRimborsoStrategy rimborso;

    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME.
     */
    public IRimborsoStrategy getRimborso() {

        if (rimborso == null) {

            Properties p = new Properties(System.getProperties());
            String rimborsoClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                rimborsoClassName = p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(rimborsoClassName).getConstructor();
                rimborso = (IRimborsoStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                rimborso = null;
            }

        }

        return rimborso;
    }

}
