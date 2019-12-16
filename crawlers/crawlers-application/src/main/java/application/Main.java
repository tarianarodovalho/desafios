package application;

import config.CrawlersConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.TelegramBotsApi;

public class Main {

	public static void main(String[] args) {
		CrawlersConfig config = new CrawlersConfig();
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotApi = new TelegramBotsApi();
		try {
			telegramBotApi.registerBot(config.TelegramBot());
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
		System.out.println("O robô está pronto para se comunicar.");
	}

}