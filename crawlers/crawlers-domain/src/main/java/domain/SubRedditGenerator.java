package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubRedditGenerator {

    public List<SubReddit> execute(HashMap<String, List<HashMap<String, String>>> subRedditsDetails) {
        List<SubReddit> generatedSubReddits = new ArrayList<>();

        for(String title: subRedditsDetails.keySet()){
            SubReddit subReddit = this.generateSubReddit(title, subRedditsDetails.get(title));
            generatedSubReddits.add(subReddit);
        }
        return generatedSubReddits;
    }

    private SubReddit generateSubReddit(String documentTitle, List<HashMap<String, String>> threadsDetails){
        List<Thread> threads = generateThreads(threadsDetails);

        SubReddit subReddit = new SubReddit();
        subReddit.setTitle(documentTitle);
        subReddit.setThreads(threads);
        return subReddit;
    }

    private List<Thread> generateThreads(List<HashMap<String, String>> threadsDetails){
        List<Thread> threads = new ArrayList<Thread>();

        for(HashMap<String, String> threadDetail: threadsDetails){
            Thread thread = new Thread();
            thread.setScore(threadDetail.get("score"));
            thread.setCommentsUrl(threadDetail.get("commentsUrl"));
            thread.setThreadUrl(threadDetail.get("threadUrl"));
            thread.setTitle(threadDetail.get("title"));
            threads.add(thread);
        }
        return threads;
    }
}
