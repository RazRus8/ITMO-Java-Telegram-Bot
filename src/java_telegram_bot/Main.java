package java_telegram_bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java_telegram_bot.bot.Bot;

import java_telegram_bot.security.Security;

public class Main
{
    public static void main(String[] args)
    {
        // create an instance with sensitive information
        Security.createInstance(Info.info()[0], Info.info()[1], Info.info()[2], Info.info()[3], Info.info()[4]);

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try
        {
            // registration and launching the bot
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }
}