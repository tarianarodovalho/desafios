package usecase;

import domain.SubRedditCrawler;
import domain.SubRedditGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

class SubRedditFinderTest {

    @Test
    void execute() throws IOException {
        String subRedditsNames = "cats;dogs";
        HashMap<String, List<HashMap<String, String>>> someSubRedditsDetails = new HashMap<>();
        SubRedditCrawler crawler = mock(SubRedditCrawler.class);
        when(crawler.getSubReddits(subRedditsNames)).thenReturn(someSubRedditsDetails);
        SubRedditGenerator generator = spy(new SubRedditGenerator());
        SubRedditFinder finder = new SubRedditFinder(crawler, generator);

        finder.execute(subRedditsNames);

        verify(generator).execute(someSubRedditsDetails);
    }
}