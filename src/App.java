
import it.unipv.trenissimo.model.persistence.service.AgencyService;
import it.unipv.trenissimo.model.persistence.service.CalendarDatesService;
import it.unipv.trenissimo.model.persistence.service.CalendarService;

import java.util.logging.Level;

public class App {

    public static void main(String[] args) {

        //Per non mostrare in console il log di cfg di hibernate non fondamentali (severe)
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

        AgencyService agencyService = new AgencyService();
        agencyService.findAll().forEach((x) -> System.out.println(x.toString()));

        CalendarDatesService calendarDatesService = new CalendarDatesService();
        calendarDatesService.findAll().forEach((x) -> System.out.println(x.toString()));

        CalendarService calendarService = new CalendarService();
        calendarService.findAll().forEach((x) -> System.out.println(x.toString()));

    }

}
