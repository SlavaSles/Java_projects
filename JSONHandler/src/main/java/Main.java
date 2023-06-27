import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonFile = Files.readString(Paths.get("data/map.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonData = objectMapper.readTree(jsonFile);
        JsonNode stations = jsonData.get("stations");
        JsonNode lines = jsonData.get("lines");
        int lineNumber = 0;
        for (JsonNode line : lines) {
            int stationsCount = 0;
            lineNumber++;
            JsonNode lineStations = stations.get(Integer.toString(lineNumber));
//            JsonNode lineStations = stations.get(line.get("number").asText());
            for (JsonNode station : lineStations) {
                stationsCount++;
            }
//            stationsCount = lineStations.size();
            ObjectNode lineNode = (ObjectNode) line;
            lineNode.remove("color");
            lineNode.put("stationsCount", stationsCount);
        }
        ObjectMapper writeMapper = new ObjectMapper();
        ObjectWriter writer = writeMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(Paths.get("data/new_map.json").toFile(), lines);
//        writeMapper.writeValue(Paths.get("data/new_map.json").toFile(), lines);
    }
}
