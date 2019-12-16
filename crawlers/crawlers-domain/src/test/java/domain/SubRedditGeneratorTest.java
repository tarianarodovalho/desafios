package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubRedditGeneratorTest {

    @Test
    void execute() {
        SubRedditGenerator generator = new SubRedditGenerator();
        HashMap<String, List<HashMap<String, String>>> subRedditsDetails = new HashMap<>();
        List<HashMap<String, String>> threads = new ArrayList<>();
        HashMap<String, String> kittens = new HashMap<>();
        kittens.put("title", "Cute Kittens");
        kittens.put("score", "5043");
        kittens.put("commentsUrl", "/kittens/comments");
        kittens.put("threadUrl", "/kittens");
        threads.add(kittens);
        subRedditsDetails.put("Cats",threads);

        List<SubReddit> subReddits = generator.execute(subRedditsDetails);

        assertEquals("Título: Cats", subReddits.get(0).getTitle());
        Thread thread = subReddits.get(0).getThreads().get(0);
        assertEquals("Título da Thread: Cute Kittens", thread.getTitle());
        assertEquals("Score: 5043", thread.getScore());
        assertEquals("URL dos comentários: /kittens/comments", thread.getCommentsUrl());
        assertEquals("URL da Thread: /kittens", thread.getThreadUrl());
    }
}