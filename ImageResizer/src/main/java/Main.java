import java.io.File;

public class Main {

    private static final int NEW_WIDTH = 300;
    private static final int CORES_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        String srcFolder = "data/images/fullsize";
        String dstFolder1 = "data/images/resize1";
        String dstFolder2 = "data/images/resize2";
        File srcDir = new File(srcFolder);
        System.out.println("Core's count: " + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int filesCount = files.length;
        int partSize = filesCount / CORES_COUNT;
        int position = 0;
        int size;
        File[][] partFile = new File[CORES_COUNT][1];
        ImageResizer[] resizer = new ImageResizer[CORES_COUNT];
        ImageResizerImgScalr[] resizerIS = new ImageResizerImgScalr[CORES_COUNT];
        for (int i = 0; i < CORES_COUNT; i++) {
            size = filesCount - partSize * (CORES_COUNT - i - 1);
            partFile[i] = new File[size];
            filesCount -= size;
            System.arraycopy(files, position, partFile[i], 0, size);
            resizer[i] = new ImageResizer(partFile[i], NEW_WIDTH, dstFolder1, start);
            resizer[i].start();
            resizerIS[i] = new ImageResizerImgScalr(partFile[i], NEW_WIDTH, dstFolder2, start);
            resizerIS[i].start();
            position += size;
        }
    }
}
