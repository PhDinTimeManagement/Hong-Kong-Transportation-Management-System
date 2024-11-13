import java.util.List;
import java.util.ArrayList;

public class Driver {
    private String driverID;
    private String driverName;
    private List<BusType> licensedBusTypes;
    private BusCompany busCompany;

    // Constructor
    public Driver (String driverID, String driverName, List<BusType> drivingLicense, BusCompany busCompany) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.licensedBusTypes = drivingLicense;
        this.busCompany = busCompany;
        busCompany.addDriver(this);
    }

    // Getters
    public String getDriverID() { return this.driverID; }
    public String getDriverName() { return this.driverName; }
    public List<BusType> getLicensedBusTypes() { return this.licensedBusTypes; }
    public BusCompany getBusCompany() { return this.busCompany; }

    // Setters
    public void setBusCompany(BusCompany busCompany) { this.busCompany = busCompany; }

    // Check if a driver is allowed to drive a specific bus type
    public boolean canDrive(BusType busType) {
        return this.licensedBusTypes.contains(busType);
    }

    // Add a bus ride
    public void addBusRide(BusRide busRide) {
        if (busRide.getDriver() == null && this.canDrive(busRide.getBus().getType())) {
            busRide.setDriver(this);
        }
    }

    // Helper method for Query 1: Check if this driver is licensed to drive a specific BusType
    public boolean isLicensedForBusType(BusType busType) {
        return licensedBusTypes.contains(busType);
    }
}
