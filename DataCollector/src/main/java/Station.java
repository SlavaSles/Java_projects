import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDate;

public class Station {
    private final String name;
    private final String lineName;
    private final String lineNumber;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate foundationDate;
    private Double depth;
    private boolean hasConnection;

    @JsonGetter("date")
    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    @JsonGetter("line")
    public String getLineName() {
        return lineName;
    }

    @JsonGetter("depth")
    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Station(String lineNumber, String name, boolean hasConnection, String lineName) {
        this.lineNumber = lineNumber;
        this.name = name;
        this.hasConnection = hasConnection;
        this.lineName = lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("hasConnection")
    public boolean isConnection() {
        return hasConnection;
    }

    public void setConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
//        return "Station{" +
//                "number=" + number +
//                ", name='" + name + '\'' +
//                '}';
        return name + " (" + "линия " + lineNumber + ")";
    }
}
