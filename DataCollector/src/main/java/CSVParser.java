import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    private final static ArrayList<StationFoundationDate> stationFoundationDates = new ArrayList<>();

    public static ArrayList<StationFoundationDate> getStationFoundationDates() {
        return new ArrayList<>(stationFoundationDates);
    }

    public static void parseCSV(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String line : lines) {
            if (!line.contains("name")) {
                String[] columns = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDate date = LocalDate.parse(columns[1], formatter);
                StationFoundationDate stationFoundationDate = new StationFoundationDate(columns[0], date);
                if (!stationFoundationDates.contains(stationFoundationDate)) {
                    stationFoundationDates.add(stationFoundationDate);
                }
            }
        }
    }
}
