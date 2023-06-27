import java.io.File;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.round;

public class Main {
    public static void main(String[] args) {
        boolean exit;
        while (true) {
            System.out.println("Введите путь к папке или файлу: ");
            String path = new Scanner(System.in).nextLine();
            if (path.equals("exit")) {
                break;
            }
            try {
                File file = new File(path);
                getHumanReadableSize(getFolderSize(file));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static long getFolderSize(File file) throws Exception {
        if (file.isFile()) {
            return file.length();
        }
        File[] files = file.listFiles();
        long folderSize = 0;
        for (File file2 : files) {
            folderSize += getFolderSize(file2);
        }
        return folderSize;
    }

    public static void getHumanReadableSize(long size) {
        String readableSize;
        readableSize = Long.toString(size);
        double shortSize = (double) size;
        if (readableSize.length() >= 3) {
            for (int i = 1; i <= (int) readableSize.length() / 3; i++) {
                shortSize = shortSize / 1024;
            }
            shortSize = (double) Math.round(shortSize * 100) / 100;
        }
        switch ((int) readableSize.length() / 3) {
            case (1) -> readableSize = Double.toString(shortSize) + " kb";
            case (2) -> readableSize = Double.toString(shortSize) + " Mb";
            case (3) -> readableSize = Double.toString(shortSize) + " Gb";
            default -> readableSize = Long.toString(size) + " b";
        }
        System.out.println("Размер выбранной папки или файла составляет: " + readableSize);
    }
//        Другая реализация
//        private static String[] sizeNames =
//                {"b", "kb", "Mb", "Gb"};
//        public static void main(String[] args) {
//            while (true) {
//                System.out.println(
//                        "Введите путь к папке или файлу: "
//                );
//                String path = new Scanner(System.in).nextLine();
//                File folder = new File(path);
//                String size =
//                        getHumanReadableSize(getFolderSize(folder));
//                System.out.println(
//                        "Размер папки / файла: " + size
//                );
//            }
//        }
//        public static long getFolderSize(File folder) {
//            if (folder.isFile()) {
//                return folder.length();
//            }
//            File[] files = folder.listFiles();
//            long length = 0;
//            for (File file : files) {
//                length += getFolderSize(file);
//            }
//            return length;
//        }
//        public static String getHumanReadableSize(long length){
//            int power = (int) (Math.log(length) / Math.log(1024));
//            double value = length / Math.pow(1024, power);
//            double roundedValue = Math.round(value * 100) / 100.;
//            return roundedValue + " " + sizeNames[power];
//        }

}
