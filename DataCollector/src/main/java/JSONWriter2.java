
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class JSONWriter2 {
    public static void mapWriter2() {
        ArrayList<Line> lines = MoscowMetroParser.getLines();
        ArrayList<Station> stations = MoscowMetroParser.getStations();

//        Вариант создания списка станций по линиям через TreeMap (линии в файле идут не по порядку (1, 10... 15, 2, 3...))
//        Map<String, String[]> stationsMap = new TreeMap<>();
//        for (Line line : lines) {
//            String[] stationArray = (String[]) stations.stream()
//                    .filter(station -> station.getNumber().equals(line.getNumber()))
//                    .map(Station::getName)
//                    .toArray(String[]::new);
//            stationsMap.put(line.getNumber(), stationArray);
//        }

//        stationsMap.keySet().forEach((key) -> {
//            System.out.println(key);
//            String[] stns = stationsMap.get(key);
//            for (int i = 0; i < stns.length; i++) {
//                System.out.print(stns[i] + ", ");
//            }
//            System.out.println();
//        });
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jLines = mapper.valueToTree(lines);
//        for (JsonNode line : jLines) {
//            System.out.println(line.get("number") + " - " + line.get("name"));
//        }
//        JsonNode jStations = mapper.valueToTree(stationsMap);
        JsonNode jStations = mapper.createObjectNode();
        for (Line line : lines) {
            String[] sttns = stations.stream().
                    filter(station -> station.getLineNumber().equals(line.getNumber())).
                    map(station -> station.getName()).toArray(String[]::new);
            JsonNode jSttns = mapper.valueToTree(sttns);
            ObjectNode sttnsNode = (ObjectNode) ((ObjectNode) jStations).set(line.getNumber(), jSttns);
        }
        JsonNode rootNode = mapper.createObjectNode();
        ObjectNode stationsNode = (ObjectNode) ((ObjectNode) rootNode).set("stations", jStations);
        ObjectNode linesNode = (ObjectNode) ((ObjectNode) rootNode).set("lines", jLines);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(Paths.get("data/map.json").toFile(), rootNode);
            //Красивую печать массива станций по линиям в отдельных строках победить не смог.
            //DefaultPrettyPrinter lines печает правильно, а stations почему-то нет...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stationsWriter2() {
        ArrayList<Line> lines = MoscowMetroParser.getLines();
        ArrayList<Station> stations = MoscowMetroParser.getStations();
        ArrayList<StationFoundationDate> stationFoundationDates = CSVParser.getStationFoundationDates();
        ArrayList<StationDepth> stationsDepth = JSONParser.getStationsDepth();

////        HashSet<String> stationNames = new HashSet<>(stations.stream().
////                map(station -> station.getName()).collect(Collectors.toSet()));
////        HashMap<String, Integer> dublicatesConnected = new HashMap<>();
////        HashMap<String, Integer> dublicatesDisconnected = new HashMap<>();
////        HashMap<String, Integer> otherDublicates = new HashMap<>();
////        HashMap<String, Integer> dublicates = new HashMap<>();
////        HashMap<String, Integer> stationDates = new HashMap<>();
////        int countConnected, countDisconnected, otherCount, datesCount;
////        for (String stationName : stationNames) {
////            countConnected = (int) stations.stream().filter(station -> station.getName().equals(stationName))
////                    .filter(station -> station.isConnection()).count();
////            countDisconnected = (int) stations.stream().filter(station -> station.getName().equals(stationName))
////                    .filter(station -> !station.isConnection()).count();
////            otherCount = (int) stations.stream().filter(station -> station.getName().equals(stationName)).count();
////            if (countConnected > 1) {
////                dublicatesConnected.put(stationName + " connected", countConnected);
////                dublicates.put(stationName, countConnected);
//////                System.out.println(stationName + " - " + countConnected + " connected");
////            }
////            if (countDisconnected > 1) {
////                dublicatesDisconnected.put(stationName + " disconnected", countDisconnected);
////                dublicates.put(stationName, countDisconnected);
//////                System.out.println(stationName + " - " + countConnected + " disconnected");
////            }
////            if (countDisconnected <= 1 && countConnected <= 1 && otherCount > 1) {
////                otherDublicates.put(stationName + " other", otherCount);
////                dublicates.put(stationName, otherCount);
//////                System.out.println(stationName + " - " + otherCount + " other");
////            }
////            datesCount = (int) stationFoundationDates.stream().filter(station -> station.getName().equals(stationName)).count();
////            if (datesCount > 1) {
////                stationDates.put(stationName, datesCount);
//////                System.out.println(stationName + " - " + datesCount + " dates");
////            }
//////            for (Station station : stations) {
//////
//////            }
////
////        }
//////        dublicatesConnected.entrySet().forEach(System.out::println);
////        dublicatesDisconnected.entrySet().forEach(System.out::println);
////        otherDublicates.entrySet().forEach(System.out::println);
////        System.out.println("\n\n");
////        for (String station : dublicates.keySet()) {
////            stationDates.entrySet().stream()
////                    .filter(sttn -> sttn.getKey().equals(station))
////                    .filter(sttn -> sttn.getValue() != dublicates.get(station))
////                    .forEach(sttn -> {
////                        System.out.println(sttn.getKey() + " - " + sttn.getValue());
////                        System.out.println(station + " * " + dublicates.get(station));
////                        System.out.println();
////                    });
////        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jStations = mapper.createObjectNode();
//        JsonNode jsonStations = jStations.path("stations");
        JsonNode jNodes = ((ObjectNode) jStations).putArray("stations");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int i = 0;
        for (Station station : stations) {
//            ObjectNode jsonStation = (ObjectNode) ((ObjectNode) jsonStations);
            ObjectNode jsonStation = mapper.createObjectNode();
            JsonNode jStationName = mapper.convertValue(station.getName(), JsonNode.class);
            ObjectNode stationName = (ObjectNode) ((ObjectNode) jsonStation).set("name", jStationName);
            for (Line line : lines) {
                if (line.getNumber().equals(station.getLineNumber())) {
                    JsonNode jLineName = mapper.convertValue(line.getName(), JsonNode.class);
                    ObjectNode lineName = (ObjectNode) ((ObjectNode) jsonStation).set("line", jLineName);
                    break;
                }
            }
            for (StationFoundationDate stationFoundationDate : stationFoundationDates) {
                if (stationFoundationDate.getName().equals(station.getName())) {
                    JsonNode jDate = mapper.
                            convertValue(formatter.format(stationFoundationDate.getFoundationDate()), JsonNode.class);
                    ObjectNode Date = (ObjectNode) ((ObjectNode) jsonStation).set("date", jDate);
                    break;
                }
            }
            for (StationDepth stationDepth : stationsDepth) {
                if (stationDepth.getName().equals(station.getName())) {
                    JsonNode jDepth = mapper.convertValue(stationDepth.getDepth(), JsonNode.class);
                    ObjectNode Depth = (ObjectNode) ((ObjectNode) jsonStation).set("depth", jDepth);
                    break;
                }
            }
            JsonNode jConnection = mapper.convertValue(station.isConnection(), JsonNode.class);
            ObjectNode connection = (ObjectNode) ((ObjectNode) jsonStation).set("hasConnection", jConnection);

            ObjectNode jsStation = (ObjectNode) ((ArrayNode) jNodes).set(i, jsonStation);
//            ObjectNode jNodes = (ObjectNode) ((ObjectNode) jStations).putArray("stations");
            i++;
        }
    }
}
