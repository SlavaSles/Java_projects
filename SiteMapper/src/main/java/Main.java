import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static final String DOMAIN = "https://sibgenco.ru/";

    public static void main(String[] args) {

        TreeSet<String> subUrls = new TreeSet<>();
        subUrls.add(DOMAIN);

        subUrls.addAll( new ForkJoinPool().invoke(new Mapper(DOMAIN, subUrls)));


        PrintWriter mapWriter;
        try {
            mapWriter = new PrintWriter("data/map.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (String subUrl : subUrls) {
            int slashCounts = subUrl.length() - subUrl.replaceAll("/", "").length();
            String newUrl = "";
            for (int i = 4; i <= slashCounts; i++) {
                newUrl = newUrl.concat("\t");
            }
            newUrl = newUrl.concat(subUrl);
            mapWriter.write(newUrl.concat("\n"));
            System.out.println(newUrl);
        }
        mapWriter.flush();
        mapWriter.close();

        System.out.println(subUrls.size());
    }
}
