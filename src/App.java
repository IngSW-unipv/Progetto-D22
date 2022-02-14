import it.unipv.trenissimo.model.persistence.AgencyService;
import it.unipv.trenissimo.model.persistence.entity.AgencyEntity;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AgencyService agencyService = new AgencyService();
        for(AgencyEntity agencies : agencyService.findAll()){
            System.out.println("Agency (" + agencies.getAgencyId() + ") : " + agencies.getAgencyUrl());
        }
    }

}
