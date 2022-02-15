import it.unipv.trenissimo.model.persistence.AgencyService;
import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.logging.Level;

public class App {

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); //Per non mostrare in console il log di cfg di hibernate non fondamentali (severe)
        AgencyService agencyService = new AgencyService();
        for(AgencyEntity agencies : agencyService.findAll()){
            System.out.println("Agency (" + agencies.getAgencyId() + ") : " + agencies.getAgencyUrl());
        }
    }

}
