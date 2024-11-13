import java.util.List;
import java.util.ArrayList;

public class BusStation {
    private String stationID;
    private List<BusLine> busLines;

    // Constructor
    public BusStation(String stationID) {
        this.stationID = stationID;
        this.busLines = new ArrayList<>();
    }

    // Getters
    public String getStationID() { return stationID; }
    public List<BusLine> getBusLines() { return busLines; }

    // Add line
    public void addBusLine(BusLine busLine) {
        if (!busLines.contains(busLine)) {
            busLines.add(busLine);
            busLine.addBusStation(this);
        }
    }
}
