package it.unipv.po.aioobe.trenissimo.model.acquisto.util.strategy;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

/**
 * Classe mediante la quale in base al property name passato da file di configurazione possiamo decidere quale strategy istanziare
 *
 * @author ArrayIndexOutOfBoundsException
 */
public class PuntiFedeltaFactory {

    /**
     * Stringa mediante la quale possiamo andare a trovare il nome effettivo della strategy da voler implementare.
     * Nel file di configurazione, a questa stringa verrà assegnato il percorso della classe strategy da istanziare
     */
    private final String PROPERTYNAME = "puntiFedelta.strategy.class.name";
    private IPuntiFedeltaStrategy puntiFedelta;

    /**
     * @return istanza della strategy scelta mediante il PROPERTYNAME
     */
    public IPuntiFedeltaStrategy getPuntiFedelta() {

        if (puntiFedelta == null) {

            Properties p = new Properties(System.getProperties());
            String puntiFedeltaClassName;

            try {
                p.load(new FileInputStream("src/main/resources/it/unipv/po/aioobe/trenissimo/properties/properties"));
                puntiFedeltaClassName = p.getProperty(PROPERTYNAME);

                Constructor c = Class.forName(puntiFedeltaClassName).getConstructor();
                puntiFedelta = (IPuntiFedeltaStrategy) c.newInstance();

            } catch (Exception e) {
                e.printStackTrace();
                puntiFedelta = null;
            }

        }

        return puntiFedelta;
    }

}
