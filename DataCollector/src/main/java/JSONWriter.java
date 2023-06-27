
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JSONWriter {
    public static void mapWriter() {
        ArrayList<Line> lines = MoscowMetroParser.getLines();
        ArrayList<Station> stations = MoscowMetroParser.getStations();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jLines = mapper.valueToTree(lines);
        ObjectNode jStations = mapper.createObjectNode();
        for (Line line : lines) {
            String[] sttns = stations.stream().
                    filter(station -> station.getLineNumber().equals(line.getNumber())).
                    map(station -> station.getName()).toArray(String[]::new);
            JsonNode jSttns = mapper.valueToTree(sttns);
            jStations.set(line.getNumber(), jSttns);
        }
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("stations", jStations);
        rootNode.set("lines", jLines);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get("data/map.json").toFile(), rootNode);
            //Красивую печать массива станций по линиям в отдельных строках победить не смог.
            //DefaultPrettyPrinter lines печает правильно, а stations почему-то нет...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stationsWriter() {
        ArrayList<Station> stations = MoscowMetroParser.getStations();
        ArrayList<StationFoundationDate> stationFoundationDates = CSVParser.getStationFoundationDates();
        ArrayList<StationDepth> stationsDepth = JSONParser.getStationsDepth();
        for (Station station : stations) {
            for (StationFoundationDate stationFoundationDate : stationFoundationDates) {
                if (stationFoundationDate.getName().equals(station.getName()) && !stationFoundationDate.isUsed()) {
                    station.setFoundationDate(stationFoundationDate.getFoundationDate());
                    stationFoundationDate.setUsed(true);
                    break;
                }
            }
            for (StationDepth stationDepth : stationsDepth) {
                if (stationDepth.getName().equals(station.getName())) {
                    station.setDepth(stationDepth.getDepth());
                    break;
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        mapper.setDateFormat(df);
        JsonNode jStationsExtended = mapper.valueToTree(stations);
        JSONArray jsttns = new JSONArray();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (Station sttn : stations) {
            JSONObject singlesttn = new JSONObject();
            try {
                singlesttn.put("name", sttn.getName());
                singlesttn.put("line", sttn.getLineName());
                if (sttn.getFoundationDate() != null) {
                    singlesttn.put("date", formatter.format(sttn.getFoundationDate()));
                }
                if (sttn.getDepth() != null) {
                    singlesttn.put("depth", sttn.getDepth());
                }
                if (sttn.isConnection()) {
                    singlesttn.put("hasConnection", sttn.isConnection());
                }
                jsttns.put(singlesttn);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        JSONObject jsttnsobj = new JSONObject();
        String jsonStations;
        try {
            jsttnsobj.put("stations", jsttns);
            jsonStations = jsttnsobj.toString(2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        try {
            PrintWriter fileWriter = new PrintWriter("data/stations_2.json");
            fileWriter.write(jsonStations);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (JsonNode jStation : jStationsExtended) {
            ObjectNode jStationNode = (ObjectNode) jStation;
            jStationNode.remove("lineNumber");
            if (jStation.get("date").asText().equals("null")) {
                jStationNode.remove("date");
            }
            if (jStation.get("depth").asText().equals("null")) {
                jStationNode.remove("depth");
            }
        }
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.set("stations", jStationsExtended);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get("data/stations.json").toFile(), rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
