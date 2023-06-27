import java.util.Objects;

public class StationDepth {
    private final String name;
    private final Double depth;

    public StationDepth(String name, Double depth) {
        this.name = name;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public Double getDepth() {
        return depth;
    }

    @Override
    public String toString() {
//        return "StationDepth{" +
//                "name='" + name + '\'' +
//                ", depth=" + depth +
//                '}';
        return "Станция: " + name + ", глубина залегания: " + depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationDepth that = (StationDepth) o;
//        && Objects.equals(depth, that.depth)
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depth);
    }
}
