import java.util.List;
import java.util.ArrayList;

public class BusLine {
    private String busLineID;
    private BusCompany busCompany;
    private List<BusStation> busStations;
    private List<BusType> allowedBusTypes;
    private List<Bus> buses;
    private List<BusRide> busRides;

    // Constructor
    public BusLine(String busLineID, BusCompany busCompany) {
        this.busLineID = busLineID;
        this.busCompany = busCompany;
        this.busStations = new ArrayList<>();
        this.allowedBusTypes = new ArrayList<>();
        this.buses = new ArrayList<>();
        this.busRides = new ArrayList<>();
        busCompany.addBusLine(this);
    }

    // Getters
    public String getBusLineID() { return busLineID; }
    public BusCompany getBusCompany() {return busCompany;}
    public List<BusStation> getBusStations() {return busStations;}
    public List<BusType> getAllowedBusTypes() {return allowedBusTypes;}
    public List<Bus> getBuses() {return buses;}
    public List<BusRide> getBusRides() {return busRides;}

    // Setters
    public void setBusCompany(BusCompany busCompany) { this.busCompany = busCompany; }

    public void addBusStation(BusStation busStation) {
        if (!busStations.contains(busStation)) {
            busStations.add(busStation);
            busStation.addBusLine(this);
        }
    }

    // Validate to ensure the bus line has at least 2 stations
    public boolean hasSufficientStations() {
        return busStations.size() >= 2;
    }

    // Add allowed bus type
    public void addAllowedBusType(BusType type) {
        if (!allowedBusTypes.contains(type)) {
            allowedBusTypes.add(type);
        }
    }

    // Add bus
    public void addBus(Bus bus) {
        if (!buses.contains(bus) && allowedBusTypes.contains(bus.getType())) {
            buses.add(bus);
            bus.addBusLine(this);
        }
    }

    // Add bus ride
    public void addBusRide(BusRide busRide) {
        if (busRide.getBusLine() == this) {
            busRides.add(busRide);
        }
    }

    // Helper method for Query 1: Check if a BusType is allowed on this line
    public boolean isBusTypeAllowed(BusType busType) {
        return allowedBusTypes.contains(busType);
    }
}
