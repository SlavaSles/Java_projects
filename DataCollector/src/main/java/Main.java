import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            MoscowMetroParser.parseMoscowMetro();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONWriter.mapWriter();
        String path = "data";
        List<String> files = FileFinder.findFiles(path);
        System.out.println("Список файлов из архива:");
        files.forEach(System.out::println);
        for (String file : files) {
            if (file.contains("dates")) {
                CSVParser.parseCSV(file);
            }
            if (file.contains("depth")) {
                JSONParser.parseJson(file);
            }
        }

//        CSVParser.getStationFoundationDates().forEach(System.out::println);
//        JSONParser.getStationsDepth().forEach(System.out::println);
        JSONWriter.stationsWriter();
    }
    
}
