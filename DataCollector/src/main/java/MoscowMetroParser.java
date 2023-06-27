import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class MoscowMetroParser {
    private static final ArrayList<Line> lines = new ArrayList<>();
    private static final ArrayList<Station> stations = new ArrayList<>();

    public static ArrayList<Line> getLines() {
        return lines;
    }

    public static ArrayList<Station> getStations() {
        return stations;
    }

    public static void parseMoscowMetro() throws IOException {
        Document doc = downloadHtml();
        metroParser(doc);
        System.out.println("Список линий метро Москвы:");
        lines.forEach(System.out::println);
        System.out.println("Список станций по линиям метро Москвы:");
        stations.forEach(System.out::println);
    }

    private static Document downloadHtml() throws IOException {
        String url = "https://skillbox-java.github.io/";
        return Jsoup.connect(url).get();

    }
    private static void metroParser(Document doc) {
        Elements lineNames = doc.select("span[data-line]");
        lineNames.forEach(lineName -> {
            String lineNumber = lineName.attr("data-line");
            lines.add(new Line(lineNumber, lineName.text()));
            String ccsQuery = "[data-line='" + lineNumber + "'][style*=grid]";
            Elements stationsOfLine = doc.select(ccsQuery);
            Elements stationList = stationsOfLine.select("p.single-station");
            stationList.forEach(station -> {
                Elements stationName = station.select("span.name");
                Elements hasConnection = station.select("[title*=переход]");
                boolean connection = !hasConnection.isEmpty();
                stations.add(new Station(lineNumber, stationName.text(), connection, lineName.text()));
            });
        });
    }
}
