
import it.unipv.trenissimo.model.persistence.service.*;

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

        RoutesService routesService = new RoutesService();
        routesService.findAll().forEach((x) -> System.out.println(x.toString()));


        StopsService stopsService = new StopsService();
        stopsService.findAll().forEach((x) -> System.out.println(x.toString()));


        StopTimesService stopTimesService = new StopTimesService();
        stopTimesService.findAll().forEach((x) -> System.out.println(x.toString()));

        TripsService tripsService = new TripsService();
        tripsService.findAll().forEach((x) -> System.out.println(x.toString()));


    }

}
