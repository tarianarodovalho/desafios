package presenter;

import controller.CrawlersController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private CrawlersController controller;

    public TelegramBot(String botUsername, String botToken, CrawlersController controller){
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.controller = controller;
    }

    /**
     *  Method for receiving messages.
     *  @param update Contains a message from the user
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userCommand = "/NADAPRAFAZER";
            String userMessage = update.getMessage().getText();
            if(userMessage.toUpperCase().startsWith(userCommand)){
                String userText = userMessage.replaceFirst(userCommand, "");
                try {
                    sendMsg(chatId, controller.replyUser(userText));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sendMsg(chatId, "Não sei do que estás falando. Por favor, digite o comando " +
                        "/NadaPraFazer seguido da lista de subReddits que desejas buscar separados por ';'.");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    /**
     * Method for creating a message and sending it.
     * @param chatId chat id
     * @param messageText the text that you want to send.
     */
    private synchronized void sendMsg(String chatId, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
