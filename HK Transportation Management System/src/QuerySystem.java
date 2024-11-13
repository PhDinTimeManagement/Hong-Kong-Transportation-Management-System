import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QuerySystem {
    // Query 1: Check if a bus type is allowed on a line and if a driver can drive that type
    public static void checkBusTypeAndDriver(BusType busType, BusLine busLine, Driver driver) {
        boolean isTypeAllowedOnLine = busLine.isBusTypeAllowed(busType);
        boolean isDriverLicensed = driver.isLicensedForBusType(busType);

        System.out.println("Query 1 Results:");
        System.out.println("Bus Type " + busType + " allowed on Bus Line " + busLine.getBusLineID() + ": " + isTypeAllowedOnLine);
        System.out.println("Driver " + driver.getDriverName() + " licensed for Bus Type " + busType + ": " + isDriverLicensed);
    }

    // Query 2: Find all bus rides on a particular bus line and date
    public static List<BusRide> findRidesOnDate(BusLine busLine, LocalDate date) {
        List<BusRide> ridesOnDate = new ArrayList<>();

        for (BusRide ride : busLine.getBusRides()) {
            if (ride.occurredOnDate(date)) {
                ridesOnDate.add(ride);
            }
        }

        // Display results
        System.out.println("Query 2 Results: Bus Rides on " + date + " for Bus Line " + busLine.getBusLineID());
        for (BusRide ride : ridesOnDate) {
            System.out.println("Bus ID: " + ride.getBus().getBusID() + ", Driver: " + ride.getDriver().getDriverName() +
                    ", Start Time: " + ride.getStartTime() + ", End Time: " + (ride.isOngoing() ? "Ongoing" : ride.getEndTime()));
        }

        return ridesOnDate;
    }
}
