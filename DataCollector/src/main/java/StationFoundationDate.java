import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class StationFoundationDate {
    private final String name;
    private final LocalDate foundationDate;
    private boolean used = false;

    public StationFoundationDate(String name, LocalDate foundationDate) {
        this.name = name;
        this.foundationDate = foundationDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public String toString() {
//        return "StationFoundationDate{" +
//                "name='" + name + '\'' +
//                ", foundationDate=" + foundationDate +
//                '}';
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "Станция: " + name + ", дата основания: " + formatter.format(foundationDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationFoundationDate that = (StationFoundationDate) o;
        return Objects.equals(name, that.name) && Objects.equals(foundationDate, that.foundationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foundationDate);
    }
}

