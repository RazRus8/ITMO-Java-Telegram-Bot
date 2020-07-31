package java_telegram_bot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java_telegram_bot.bot.userInputParse.UserInputParse;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Bot extends TelegramLongPollingBot
{
    private void output(String message, Update update)
    {
        SendMessage sendMessage = new SendMessage();

        if (message != null)
        {
            // message send to user
            sendMessage.setText(message);
        }

        sendMessage.setChatId(update.getMessage().getChatId());

        try
        {
            execute(sendMessage);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        System.out.println("\nUser (" + update.getMessage().getFrom().getUserName() + ") command: " + update.getMessage().getText());

        String command = update.getMessage().getText();
        Location location = update.getMessage().getLocation();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(8);

        if (command != null)
        {
            executor.submit(()->
            {
                output(UserInputParse.parseCommand(update, command), update);
            });
        }
        else if (location != null)
        {
            executor.submit(()->
            {
                output(UserInputParse.parseLocation(update, location), update);
            });
        }
        else
        {
            output("Something went wrong.", update);
        }
    }

    @Override
    public String getBotUsername()
    {
        return java_telegram_bot.security.Security.getInstance().getBotUserName();
    }

    @Override
    public String getBotToken()
    {
        return java_telegram_bot.security.Security.getInstance().getBotToken();
    }
}