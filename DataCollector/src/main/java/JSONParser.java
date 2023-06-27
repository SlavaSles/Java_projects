import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONParser {
    private final static ArrayList<StationDepth> stationsDepth = new ArrayList<>();

    public static ArrayList<StationDepth> getStationsDepth() {
        return new ArrayList<>(stationsDepth);
    }

    public static void parseJson(String path) {
        String jsonFile = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonFile = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JsonNode jsonData = objectMapper.readTree(jsonFile);
            for (JsonNode jdata : jsonData) {
                StationDepth stationDepth = new StationDepth(jdata.get("station_name").asText(),
                        jdata.get("depth").asDouble());
                if (stationsDepth.contains(stationDepth)) {
                    int index = stationsDepth.indexOf(stationDepth);
                    if (stationsDepth.get(index).getDepth() > stationDepth.getDepth()) {
                        stationsDepth.set(index, stationDepth);
                    }
                } else {
                    stationsDepth.add(stationDepth);
                }

            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
