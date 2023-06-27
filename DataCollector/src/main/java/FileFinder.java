import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    private final static List<String> filePathes = new ArrayList<>();
    public static List<String> findFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subfile : files) {
                findFiles(subfile.getAbsolutePath());
            }
        } else {
            filePathes.add(file.getAbsolutePath());
        }
        return new ArrayList<>(filePathes );
    }
}
