package it.unipv.po.aioobe.trenissimo.model;

import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedStopsService;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.CachedTripsService;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

//TODO: non in UML
public class Utils {
    public static Integer timeToSeconds(String time) {
        var hh = time.substring(0,2);
        var mm = time.substring(3,5);
        var ss = time.substring(6,8);
        return Integer.parseInt(hh) * 3600 + Integer.parseInt(mm) * 60 + Integer.parseInt(ss);
    }

    public static String secondsToTime(Integer seconds){
        return secondsToTime(seconds, true);
    }

    public static String secondsToTime(Integer seconds, boolean showSeconds){
        long HH = seconds / 3600;
        long MM = (seconds % 3600) / 60;
        long SS = seconds % 60;
        if (showSeconds){
            return String.format("%02d:%02d:%02d", HH, MM, SS);
        } else {
            return String.format("%02d:%02d", HH, MM);
        }
    }

    public static void printViaggio(Viaggio v){
        var result = v.getCambi();

        System.out.println("Partenza: " + Utils.secondsToTime(v.getStazionePartenza().getStopId()) + " - Durata: " + Utils.secondsToTime(v.getDurata()));
        System.out.println("Cambi: " + (result.stream().map(x -> x.getDepartureStationTrip()).distinct().count() - 1));
        for (Connection x : result) {
            var routeFrom = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() ==x.getDepartureStationTrip()).findFirst().get().getRouteId();
            var routeTo = CachedTripsService.getInstance().findAll().stream().filter(y -> y.getTripId() == x.getArrivalStationTrip()).findFirst().get().getRouteId();
            System.out.println(
                    "[" + routeFrom + "] " + CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.getDepartureStation()).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.getDepartureTimestamp())
                            + ") -> [" + routeTo + "] " +  CachedStopsService.getInstance().findAll().stream().filter(y -> y.getStopId() == x.getArrivalStation()).findAny().get().getStopName() + " (" + Utils.secondsToTime(x.getArrivalTimestamp()) + ")");
        }
        System.out.println("\n\n");
    }
}
