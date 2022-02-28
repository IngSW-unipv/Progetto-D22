package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca;

import it.unipv.po.aioobe.trenissimo.model.Utils;
import it.unipv.po.aioobe.trenissimo.model.persistence.entity.*;
import it.unipv.po.aioobe.trenissimo.model.persistence.service.*;
import it.unipv.po.aioobe.trenissimo.model.viaggio.Viaggio;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ViaggioAlt;
import it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils.Connection;

import java.util.*;

//TODO: non in UML

/**
 * See <a href="https://arxiv.org/pdf/1703.05997.pdf">Connection Scan Algorithm</a>
 */
public class CSASearch {
    public static final int MAX_STATIONS = 100000;


    private List<Connection> generaTimetable(List<RoutesEntity> routesList, List<StopsEntity> stopsList, List<StopTimesEntity> stopTimesList, List<TripsEntity> tripsList) {
        List<Connection> timetable = new ArrayList<Connection>();

        // Per ogni tratta... (Routes)
        for (RoutesEntity route : routesList) {
            var thisTrip = tripsList.stream().filter(x -> x.getRouteId().equals(route.getRouteId())).toList();

            // Per ogni orario possibile di tale tratta...
            for (TripsEntity trip : thisTrip) {
                // Prendo tutte le fermate che fa in ordine cronologico...
                var tripStopTimes = stopTimesList.stream().filter(x -> x.getTripId() == trip.getTripId()).sorted((Comparator.comparing(o -> o.getArrivalTime()))).toList();

                // Creo connessioni ordinate cronologicamente delle stazioni
                for (int i = 0; i < tripStopTimes.size() - 1; i++) {
                    var thisNode = tripStopTimes.get(i);
                    var nextNode = tripStopTimes.get(i + 1);
                    timetable.add(
                            new Connection(
                                    thisNode.getStopId(),
                                    nextNode.getStopId(),
                                    Utils.timeToSeconds(thisNode.getDepartureTime()),
                                    Utils.timeToSeconds(nextNode.getArrivalTime()),
                                    thisNode.getTripId(),
                                    nextNode.getTripId()
                            ));
                }
            }
        }

        // Per permettere i cambi prendo ogni stazione...
        for (StopsEntity stop : stopsList) {
            // di ogni stazione trovo tutti i possibili treni che passano durante la giornata
            var trainStopTimes = stopTimesList.stream().filter(x -> x.getStopId() == stop.getStopId()).toList();
            for (StopTimesEntity nodeA : trainStopTimes) {
                for (StopTimesEntity nodeB : trainStopTimes) {
                    // Collego tutte le fermate trovate con fermate successive
                    if (
                            !nodeA.equals(nodeB) // non collego fermate uguali
                                    && Utils.timeToSeconds(nodeA.getArrivalTime()) < Utils.timeToSeconds(nodeB.getArrivalTime()) // non collego fermate con tempo di percorrenza negativo
                                    && (Utils.timeToSeconds(nodeB.getArrivalTime()) - Utils.timeToSeconds(nodeA.getArrivalTime())) < 3600 // non collego fermate con tempo di percorrenza superiore a un'ora
                    ) {
                        timetable.add(
                                new Connection(
                                        nodeA.getStopId(),
                                        nodeB.getStopId(),
                                        Utils.timeToSeconds(nodeA.getDepartureTime()),
                                        Utils.timeToSeconds(nodeB.getArrivalTime()),
                                        nodeA.getTripId(),
                                        nodeB.getTripId()
                                ));
                    }
                }
            }
        }

        return timetable;
    }

    private List<Connection> compute(int departure_station, int arrival_station, int departure_time, List<Connection> timetable) {
        Connection in_connection[] = new Connection[MAX_STATIONS];
        int earliest_arrival[] = new int[MAX_STATIONS];
        for (int i = 0; i < MAX_STATIONS; ++i) {
            in_connection[i] = null;
            earliest_arrival[i] = Integer.MAX_VALUE;
        }
        earliest_arrival[departure_station] = departure_time;

        if (departure_station <= MAX_STATIONS && arrival_station <= MAX_STATIONS) {
            // CSA Main loop
            int earliest = Integer.MAX_VALUE;
            timetable = timetable.stream().sorted(Comparator.comparingInt(x -> x.arrival_timestamp)).toList();

            for (Connection connection : timetable) {
                if (connection.departure_timestamp >= earliest_arrival[connection.departure_station] &&
                        connection.arrival_timestamp < earliest_arrival[connection.arrival_station]) {
                    earliest_arrival[connection.arrival_station] = connection.arrival_timestamp;
                    in_connection[connection.arrival_station] = connection;

                    if (connection.arrival_station == arrival_station) {
                        earliest = Math.min(earliest, connection.arrival_timestamp);
                    }
                } else if (connection.arrival_timestamp > earliest) {
                    continue;
                }
            }
        }

        // Reprocess result
        if (in_connection[arrival_station] == null) {
            return null;
        } else {
            List<Connection> route = new ArrayList<Connection>();
            Connection last_connection = in_connection[arrival_station];
            while (last_connection != null) {
                route.add(last_connection);
                last_connection = in_connection[last_connection.departure_station];
            }
            Collections.reverse(route);
            return route;
        }
    }

    public List<ViaggioAlt> eseguiRicerca(int departureStopId, int arrivalStopId, int lastTime) {


        // Database data load
        // TODO: da mettere in cache all'avvio del programma (Splashscreen), questi dati in genere non cambiano velocemente

        List<RoutesEntity> routesList = CachedRoutesService.getInstance().findAll();
        List<StopsEntity> stopsList = CachedStopsService.getInstance().findAll();
        List<StopTimesEntity> stopTimesList = CachedStopTimesService.getInstance().findAll();
        List<TripsEntity> tripsList = CachedTripsService.getInstance().findAll();


        var timetable = generaTimetable(routesList, stopsList, stopTimesList, tripsList);


        // compute(...) tira fuori solo il primo viaggio possibile a partire dal departure_time, bisogna ciclare finche ritorna null
        // TODO: da rivedere, magari fatto meglio? sto while true non mi piace

        List<ViaggioAlt> viaggi = new ArrayList<ViaggioAlt>();

        while (true) {
            var result = compute(departureStopId, arrivalStopId, lastTime, timetable);
            if (result == null) { break; }

            var viaggio = new ViaggioAlt();
            viaggio.setCambi(result);
            viaggi.add(viaggio);



            lastTime = result.get(0).departure_timestamp + 1;
        }


        return viaggi;
    }


}
