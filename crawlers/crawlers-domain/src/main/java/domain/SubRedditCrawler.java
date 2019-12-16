package domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface SubRedditCrawler {

    HashMap<String, List<HashMap<String, String>>> getSubReddits(String subRedditsNames) throws IOException;
}
