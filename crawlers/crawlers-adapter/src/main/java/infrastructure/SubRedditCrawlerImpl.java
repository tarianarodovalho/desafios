package infrastructure;

import domain.SubRedditCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubRedditCrawlerImpl implements SubRedditCrawler {

    private int minimumBombasticScore;
    private String redditEndpoint;

    public SubRedditCrawlerImpl(Integer minimumBombasticScore, String redditEndpoint){
        this.minimumBombasticScore = minimumBombasticScore;
        this.redditEndpoint = redditEndpoint;
    }

    public HashMap<String, List<HashMap<String, String>>> getSubReddits(String subRedditsNames) throws IOException {
        List<String> subRedditsResources = Arrays.asList(subRedditsNames.trim().split(";"));
        HashMap<String, List<HashMap<String, String>>> subReddits = new HashMap<>();

        for (String resource : subRedditsResources){
            String subRedditEndpoint = redditEndpoint + resource;
            Document document = Jsoup.connect(subRedditEndpoint).get();

            List<HashMap<String, String>> highScoreThreads = new ArrayList<>();

            Element content = document.getElementById("siteTable");
            Elements elements = content.getElementsByClass("thing");
            for(Element element: elements) {
                String elementScore = element.attr("data-score");
                if (!elementScore.isEmpty()) {
                    int score = Integer.parseInt(elementScore);
                    if (score >= minimumBombasticScore) {
                        HashMap<String, String> threadDetails = new HashMap<>();
                        threadDetails.put("score", element.attr("data-score"));
                        threadDetails.put("commentsUrl", element.attr("data-permalink"));
                        threadDetails.put("threadUrl", element.attr("data-url"));
                        threadDetails.put("title", element.select("a.title").text());
                        highScoreThreads.add(threadDetails);
                    }
                }
            }
            subReddits.put(document.title(), highScoreThreads);
        }
        return subReddits;
    }

}
