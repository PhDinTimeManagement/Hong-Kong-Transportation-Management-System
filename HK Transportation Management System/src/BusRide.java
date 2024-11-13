import java.time.LocalDate;
import java.time.LocalDateTime;

public class BusRide {
    private Bus bus;
    private Driver driver;
    private final BusLine busLine;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public BusRide(Bus bus, Driver driver, BusLine busLine, LocalDateTime startTime) {
        this.bus = bus;
        this.driver = driver;
        this.busLine = busLine;
        this.startTime = startTime;
        this.endTime = null;
        bus.addBusRide(this);
        driver.addBusRide(this);
        busLine.addBusRide(this);
    }

    // Getters
    public Bus getBus() { return bus; }
    public Driver getDriver() { return driver; }
    public BusLine getBusLine() { return busLine; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime;}

    // Set bus
    public void setBus(Bus bus) { this.bus = bus; }
    public void setDriver(Driver driver) { this.driver = driver; }


    // Set end time to mark the ride as completed
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    // Check if the ride is finished
    public boolean isOngoing() {
        return endTime == null;
    }

    // Helper method for Query 2: Check if this ride occurred on a given date
    public boolean occurredOnDate(LocalDate date) {
        return startTime.toLocalDate().equals(date);
    }
}