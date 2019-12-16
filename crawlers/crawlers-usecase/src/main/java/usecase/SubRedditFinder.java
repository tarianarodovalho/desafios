package usecase;

import domain.SubReddit;
import domain.SubRedditCrawler;
import domain.SubRedditGenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubRedditFinder {

    private SubRedditCrawler crawler;
    private SubRedditGenerator subRedditGenerator;

    public SubRedditFinder(SubRedditCrawler crawler, SubRedditGenerator subRedditGenerator){
        this.crawler = crawler;
        this.subRedditGenerator = subRedditGenerator;
    }

    public List<SubReddit> execute(String subRedditsNames) throws IOException {
        HashMap<String, List<HashMap<String, String>>> subRedditsDetails = crawler.getSubReddits(subRedditsNames);
        return subRedditGenerator.execute(subRedditsDetails);
    }
}