package config;

import controller.CrawlersController;
import presenter.TelegramBot;
import domain.SubRedditGenerator;
import infrastructure.SubRedditCrawlerImpl;
import usecase.SubRedditFinder;

public class CrawlersConfig {

    private final int MINIMUM_BOMBASTIC_SCORE = 5000;
    private final String TELEGRAM_BOT_USERNAME="idwallTarianaCrawlerBot";
    private final String TELEGRAM_TOKEN = telegramToken();
    private final String REDDIT_ENDPOINT = "https://old.reddit.com/r/";

    private SubRedditGenerator subRedditGenerator = new SubRedditGenerator();
    private SubRedditCrawlerImpl subRedditCrawler = new SubRedditCrawlerImpl(MINIMUM_BOMBASTIC_SCORE, REDDIT_ENDPOINT);
    private SubRedditFinder subRedditFinder = new SubRedditFinder(subRedditCrawler, subRedditGenerator);
    private CrawlersController crawlersController = new CrawlersController(subRedditFinder);

    public TelegramBot TelegramBot() {
        return new TelegramBot(TELEGRAM_BOT_USERNAME, TELEGRAM_TOKEN, crawlersController);
    }

    public String telegramToken(){
        File resourcesDirectory = new File("../crawlers-application/src/main/resources/token.txt");
        Path path = Paths.get(resourcesDirectory.getAbsolutePath());
        try (Stream<String> token = Files.lines(path)) {
            return token;
        } catch (IOException e){
            System.out.println("Não foi possível encontrar o token do bot.");
        }
    }
}
