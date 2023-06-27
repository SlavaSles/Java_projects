import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://drom.ru/";
        String urlImage;
        Document doc = Jsoup.connect(url).get();
        Elements images = doc.select("img");
        HashSet<String> urlsImgs = new HashSet<String>();
        int number = 1;
        for (Element image : images) {
            urlImage = image.attr("src");
            urlsImgs.add(urlImage);
            String extension = urlImage.replaceAll(".+\\.", "");
            String name = "pictures/" + number++ + "." + extension;
            System.out.println(urlImage);
            try {
                download(urlImage, name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
    public static void download(String path, String name) throws IOException {
        StringBuilder file = new StringBuilder();
        URLConnection connection = new URL(path).openConnection();
//        InputStream inStream = connection.getInputStream();
//        FileOutputStream outStream = new FileOutputStream(name);
//        int b;
//        while((b = inStream.read()) != -1) {
//            outStream.write(b);
//        }
//        outStream.flush();
//        outStream.close();
//        inStream.close();
        InputStream inStream = connection.getInputStream();
        while (true) {
            int ch = inStream.read();
            if (ch < 0) { break; }
            file.append((char) ch);
        }
        inStream.close();
        FileOutputStream outStream = new FileOutputStream(name);
        for (int cursor = 0; cursor < file.length(); cursor++) {
            char ch = file.charAt(cursor);
            outStream.write((int) ch);
        }
        outStream.close();
    }
}
