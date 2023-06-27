import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper extends RecursiveTask<TreeSet<String>> {

    public static final String REGEX_1 = "href=\"" + Main.DOMAIN + "([\\w+[\\-\\w+]*][/\\w+[\\-\\w+]*]*)/\"";
    public static final String REGEX_2 = "href=\"/([\\w+[\\-\\w+]*][/\\w+[\\-\\w+]*]*)/\"";
    private static final Logger LOGGER = LogManager.getLogger(Mapper.class);
    private String subUrl;
    private TreeSet<String> parentUrls = new TreeSet<>();
    private TreeSet<String> subUrls = new TreeSet<>();

    public Mapper(String subUrl, TreeSet<String> parentUrls) {
        this.subUrl = subUrl;
        this.parentUrls = parentUrls;
    }

    @Override
    protected TreeSet<String> compute() {
        Document doc = null;
        int subUrlLevel = subUrl.length() - subUrl.replaceAll("/","").length() - 3;
        try {
            Thread.currentThread().sleep(150);
            LOGGER.info("Обращение по адресу: " + subUrl);
            doc = Jsoup.connect(subUrl).get();
        } catch (HttpStatusException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (InterruptedException e) {
            e.getMessage();
        }
        if (doc == null) {
            return subUrls;
        }
        Elements links = doc.select("[^href]");
        links.forEach(link -> {
                    String textLink = link.toString();
                    Pattern pattern1 = Pattern.compile(REGEX_1);
                    Matcher matcher1 = pattern1.matcher(textLink);
                    Pattern pattern2 = Pattern.compile(REGEX_2);
                    Matcher matcher2 = pattern2.matcher(textLink);
                    String urlLink = "";
                    if (matcher1.find()) {
                        urlLink = Main.DOMAIN.concat(matcher1.group(1).trim()).concat("/");
                    }
                    if (matcher2.find()) {
                        urlLink = Main.DOMAIN.concat(matcher2.group(1).trim()).concat("/");
                    }
                    int urlLevel = urlLink.length() - urlLink.replaceAll("/","").length() - 3;
                    if (!subUrl.equals(urlLink) && (urlLevel - subUrlLevel) >= 1 && !parentUrls.contains(urlLink)) {
                        subUrls.add(urlLink);
                    }
                }
        );
        parentUrls.addAll(subUrls);
        List<Mapper> taskList = new ArrayList<>();
        for (String subUrl : subUrls) {
            Mapper mapperTask = new Mapper(subUrl, parentUrls);
            mapperTask.fork();
            taskList.add(mapperTask);
        }
        for (Mapper mapperTask : taskList) {
            subUrls.addAll(mapperTask.join());
        }
        return subUrls;
    }
}
