package java_telegram_bot.bot;

import java_telegram_bot.bot.userInputParse.UserInputParse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot
{
    @Override
    public void onUpdateReceived(Update update)
    {
        String output;
        SendMessage message = new SendMessage();

        System.out.println("\nUser (" + update.getMessage().getFrom().getUserName() + ") command: " + update.getMessage().getText());

        String command = update.getMessage().getText();
        Location location = update.getMessage().getLocation();

        if (command != null)
        {
            output = UserInputParse.parseCommand(update, command);
        }
        else if (location != null)
        {
            output = UserInputParse.parseLocation(update, location);
        }
        else
        {
            output = "Something went wrong.";
        }

        if (output != null)
        {
            // message send to user
            message.setText(output);
        }

        message.setChatId(update.getMessage().getChatId());

        try
        {
            execute(message);
        }
        catch (TelegramApiException e)
        {
            e.printStackTrace();
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