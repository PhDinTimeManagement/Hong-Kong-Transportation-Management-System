import java.util.List;
import java.util.ArrayList;

public class BusCompany {
    private String companyName;
    private String companyID;
    private List<BusLine> busLines;
    private List<Bus> buses;
    private List<Driver> drivers;

    // Constructor
    public BusCompany(String companyName,String companyID){
        this.companyName = companyName;
        this.companyID = companyID;
        this.busLines = new ArrayList<>();
        this.buses = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    // Getters
    public String getCompanyName() {return companyName; }
    public String getCompanyID() {return companyID;}
    public List<BusLine> getBusLines() { return busLines; }
    public List<Bus> getBuses() { return buses; }
    public List<Driver> getDrivers() { return drivers; }

    // Add bus lines
    public void addBusLine(BusLine busLine) {
        if (!busLines.contains(busLine)) {
            busLines.add(busLine);
            busLine.setBusCompany(this);
        }
    }

    // Add buses
    public void addBus(Bus bus) {
        if (!buses.contains(bus)) {
            buses.add(bus);
            bus.setBusCompany(this);
        }
    }

    // Add drivers
    public void addDriver(Driver driver) {
        if (!drivers.contains(driver)) {
            drivers.add(driver);
            driver.setBusCompany(this);
        }
    }
}
