package controller;

import com.google.gson.Gson;
import domain.SubReddit;
import usecase.SubRedditFinder;

import java.io.IOException;
import java.util.List;

public class CrawlersController {

    private SubRedditFinder subRedditFinder;

    public CrawlersController(SubRedditFinder subRedditFinder){
        this.subRedditFinder = subRedditFinder;
    }

    public String replyUser(final String userText) throws IOException {
        Gson reply = new Gson();
        List<SubReddit> subReddits = this.subRedditFinder.execute(userText);
        return reply.toJson(subReddits);
    }
}
