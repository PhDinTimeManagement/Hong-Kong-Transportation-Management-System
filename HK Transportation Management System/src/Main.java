import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Set up sample data for BusCompany, BusType, BusLine, and Driver
        BusCompany busCompany1 = new BusCompany("City Transport", "CT001");
        BusCompany busCompany2 = new BusCompany("City Line", "CT002");

        // Creat sample bus lines and drivers
        BusLine busLine1 = new BusLine("Line_1", busCompany1);
        BusLine busLine2 = new BusLine("Line_2", busCompany2);
        BusLine busLine3 = new BusLine("Line_3", busCompany1);
        BusLine busLine4 = new BusLine("Line_4", busCompany2);
        busLine1.addAllowedBusType(BusType.DOUBLE_DECKER);
        busLine1.addAllowedBusType(BusType.MINI_BUS);
        busLine2.addAllowedBusType(BusType.SINGLE_DECKER);
        busLine2.addAllowedBusType(BusType.ELECTRIC);
        busLine3.addAllowedBusType(BusType.DOUBLE_DECKER);
        busLine3.addAllowedBusType(BusType.MINI_BUS);
        busLine3.addAllowedBusType(BusType.SINGLE_DECKER);
        busLine4.addAllowedBusType(BusType.ELECTRIC);

        Driver driver1 = new Driver("D001", "Alice", List.of(BusType.DOUBLE_DECKER, BusType.MINI_BUS), busCompany1);
        Driver driver2 = new Driver("D002", "Bob", List.of(BusType.SINGLE_DECKER, BusType.ELECTRIC), busCompany2);
        Driver driver3 = new Driver("D003", "Charlie", List.of(BusType.DOUBLE_DECKER, BusType.SINGLE_DECKER), busCompany1);
        Driver driver4 = new Driver("D004", "David", List.of(BusType.MINI_BUS, BusType.ELECTRIC), busCompany2);
        Driver driver5 = new Driver("D005", "Eve", List.of(BusType.DOUBLE_DECKER, BusType.MINI_BUS), busCompany1);

        // Adding drivers to the bus company
        busCompany1.addDriver(driver1);
        busCompany2.addDriver(driver2);
        busCompany1.addDriver(driver3);
        busCompany2.addDriver(driver4);
        busCompany1.addDriver(driver5);

        // Create sample bus rides
        BusRide ride1 = new BusRide(new Bus("Bus_101", BusType.DOUBLE_DECKER, 60, BusState.WORKING, busCompany1), driver1, busLine1, LocalDateTime.of(2024, 11, 12, 8, 0));
        ride1.setEndTime(LocalDateTime.of(2024, 11, 12, 9, 0)); // Completed ride
        // Ongoing ride (no end time set)
        BusRide ride2 = new BusRide(new Bus("Bus_102", BusType.MINI_BUS, 30, BusState.WORKING, busCompany1), driver1, busLine1, LocalDateTime.of(2024, 11, 12, 10, 0));
        BusRide ride3 = new BusRide(new Bus("Bus_103", BusType.SINGLE_DECKER, 40, BusState.WORKING, busCompany1), driver3, busLine3, LocalDateTime.of(2024, 11, 12, 12, 0));
        ride3.setEndTime(LocalDateTime.of(2024, 11, 12, 13, 0)); // Completed ride
        // Ongoing ride (no end time set)
        BusRide ride4 = new BusRide(new Bus("Bus_104", BusType.ELECTRIC, 50, BusState.WORKING, busCompany2), driver4, busLine4, LocalDateTime.of(2024, 11, 12, 14, 0));


        // Add the rides to their respective bus lines
        busLine1.addBusRide(ride1);
        busLine1.addBusRide(ride2);
        busLine3.addBusRide(ride3);
        busLine4.addBusRide(ride4);

        boolean keepRunning = true;

        while (keepRunning) {
            // Main Menu
            System.out.println("\nWelcome to the Bus Management System!");
            System.out.println("Please choose an option:");
            System.out.println("1. Check if a bus type is allowed on a line and if a driver is licensed for that bus type.");
            System.out.println("2. Find all bus rides on a specific bus line for a particular date.");
            System.out.print("Enter option number (1 or 2): ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            BusLine selectedBusLine;
            String lineID;

            switch (option) {
                case 1:
                    // Query 1: Check bus type and driver license
                    System.out.println("\nQuery 1: Check Bus Type and Driver License");

                    // Prompt for BusType
                    System.out.println("Available Bus Types: DOUBLE_DECKER, MINI_BUS, SINGLE_DECKER, ELECTRIC, HYBRID");
                    System.out.print("Enter the bus type: ");
                    String busTypeInput = scanner.nextLine().toUpperCase();
                    BusType busType;
                    try {
                        busType = BusType.valueOf(busTypeInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid bus type. Please restart and try again.");
                        continue;
                    }

                    // Prompt for BusLine
                    System.out.print("Enter the bus line ID (e.g., Line_1, Line_2, Line_3, Line_4): ");
                    lineID = scanner.nextLine();
                    selectedBusLine = switch (lineID) {
                        case "Line_1" -> busLine1;
                        case "Line_2" -> busLine2;
                        case "Line_3" -> busLine3;
                        case "Line_4" -> busLine4;
                        default -> null;
                    };

                    if (selectedBusLine == null) {
                        System.out.println("Invalid bus line. Please restart and try again.");
                        continue;
                    }

                    // Prompt for Driver
                    System.out.println("Available Drivers: Alice (ID: D001), Bob (ID: D002), Charlie (ID: D003), David (ID: D004), Eve (ID: D005)");
                    System.out.print("Enter the driver ID: ");
                    String driverID = scanner.nextLine();
                    Driver selectedDriver = switch (driverID) {
                        case "D001" -> driver1;
                        case "D002" -> driver2;
                        case "D003" -> driver3;
                        case "D004" -> driver4;
                        case "D005" -> driver5;
                        default -> null;
                    };

                    if (selectedDriver == null) {
                        System.out.println("Invalid driver ID. Please restart and try again.");
                        continue;
                    }

                    // Perform Query 1
                    QuerySystem.checkBusTypeAndDriver(busType, selectedBusLine, selectedDriver);
                    break;

                case 2:
                    // Query 2: Find rides on a specific date
                    System.out.println("\nQuery 2: Find Bus Rides on a Particular Date");

                    // Prompt for BusLine
                    System.out.print("Enter the bus line ID (e.g., Line_1, Line_2, Line_3, Line_4): ");
                    lineID = scanner.nextLine();
                    selectedBusLine = switch (lineID) {
                        case "Line_1" -> busLine1;
                        case "Line_2" -> busLine2;
                        case "Line_3" -> busLine3;
                        case "Line_4" -> busLine4;
                        default -> null;
                    };

                    if (selectedBusLine == null) {
                        System.out.println("Invalid bus line. Please restart and try again.");
                        continue;
                    }

                    // Prompt for Date
                    System.out.print("Today is 2024-11-12. If you are testing this system, please input this date.\nEnter the date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    LocalDate date;
                    try {
                        date = LocalDate.parse(dateInput);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD format and try again.");
                        continue;
                    }

                    // Perform Query 2
                    List<BusRide> ridesOnDate = QuerySystem.findRidesOnDate(selectedBusLine, date);

                    if (ridesOnDate.isEmpty()) {
                        System.out.println("No bus rides found on this date for the selected bus line.");
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1 or 2.");
                    continue;
            }

            // Ask if the user wants to perform another query or quit
            System.out.print("\nWould you like to perform another query? (yes or no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                keepRunning = false;
                System.out.println("Exiting the Bus Management System. Thank you!");
            }
        }

        scanner.close();
    }
}