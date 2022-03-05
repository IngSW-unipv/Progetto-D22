package it.unipv.po.aioobe.trenissimo.model.viaggio.ricerca.utils;

//TODO: non in UML
public class Connection {
    private int departure_station, arrival_station;
    private int departure_timestamp, arrival_timestamp;
    private int departure_station_trip, arrival_station_trip;

    public int getDepartureStation() {
        return departure_station;
    }

    public int getArrivalStation() {
        return arrival_station;
    }

    public int getDepartureTimestamp() {
        return departure_timestamp;
    }

    public int getArrivalTimestamp() {
        return arrival_timestamp;
    }

    public int getDepartureStationTrip() {
        return departure_station_trip;
    }

    public int getArrivalStationTrip() {
        return arrival_station_trip;
    }

    public Connection(int departure_station, int arrival_station, int departure_timestamp, int arrival_timestamp, int departure_station_trip, int arrival_station_trip) {
        this.departure_station = departure_station;
        this.arrival_station = arrival_station;
        this.departure_timestamp = departure_timestamp;
        this.arrival_timestamp = arrival_timestamp;
        this.departure_station_trip = departure_station_trip;
        this.arrival_station_trip = arrival_station_trip;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "departure_station=" + departure_station +
                ", arrival_station=" + arrival_station +
                ", departure_timestamp=" + departure_timestamp +
                ", arrival_timestamp=" + arrival_timestamp +
                ", departure_station_trip=" + departure_station_trip +
                ", arrival_station_trip=" + arrival_station_trip +
                '}';
    }
}
