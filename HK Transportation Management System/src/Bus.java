import java.util.List;
import java.util.ArrayList;

public class Bus {
    private String busID;
    private BusType busType;
    private int busCapacity;
    private final BusState busState;
    private BusCompany busCompany;
    private List<BusLine> busLines;

    public Bus(String busID, BusType busType, int busCapacity, BusState busState, BusCompany busCompany) {
        this.busID = busID;
        this.busType = busType;
        this.busCapacity = busCapacity;
        this.busState = busState;
        this.busCompany = busCompany;
        this.busLines = new ArrayList<>();
    }

    // Getters
    public String getBusID() { return busID; }
    public BusType getType() { return busType; }
    public int getBusCapacity() { return busCapacity; }
    public BusState getBusState() { return busState; }
    public BusCompany getBusCompany() { return busCompany; }
    public List<BusLine> getBusLines() { return busLines; }

    // Setters
    public void setBusCompany(BusCompany busCompany) { this.busCompany = busCompany; }

    // Add busCompany
    public void addCompany(BusCompany company) {
        if (this.busCompany == null) {
            this.busCompany = company;
            company.addBus(this);
        }
    }

    // Add bus line
    public void addBusLine(BusLine busLine) {
        if (!busLines.contains(busLine) && busLine.getAllowedBusTypes().contains(this.busType)) {
            busLines.add(busLine);
            busLine.addBus(this);
        }
    }

    // Add a bus ride
    public void addBusRide(BusRide busRide) {
        if (busLines.contains(busRide.getBusLine())) {
            busRide.setBus(this);
        }
    }
}
