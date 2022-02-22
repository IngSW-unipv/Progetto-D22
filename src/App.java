
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.AccountEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.DatiPersonaliEntity;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.CSASearch;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

public class App {

    public static void main(String[] args) throws ParseException {

        CSASearch search = new CSASearch();
        var viaggi = search.eseguiRicerca(332,2793);
        viaggi.get(0).getCambi().forEach(x -> System.out.println(x.toString()));

    }

}
