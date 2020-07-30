package java_telegram_bot.bot.userInputParse;

import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Update;
import java_telegram_bot.bot.WeatherInfo;
import java_telegram_bot.owmApi.JSON;
import java_telegram_bot.owmApi.Weather;
import java_telegram_bot.models.User;
import java_telegram_bot.models.weather.*;
import java_telegram_bot.database.DBActions;
import java_telegram_bot.security.Security;

public class UserInputParse
{
    private static double latitude;
    private static double longitude;
    private static String key = Security.getInstance().getApiKey();
    private static String message;

    public static String parseCommand(Update update, String command)
    {
        int userId = update.getMessage().getFrom().getId();
        String firstname = update.getMessage().getFrom().getFirstName();
        String lastname = update.getMessage().getFrom().getLastName();

        DBActions db = new DBActions();

        if (command.equals("/start"))
        {
            // create new user
            User user = new User(userId, update.getMessage().getFrom().getBot(), firstname, lastname, update.getMessage().getFrom().getUserName(), 0, 0, 0);

            message = "Share your location to get current weather forecast and subscribe for daily weather forecast.";

            if (db.DBSelectUser(userId) == null)
            {
                // to do: make async work with DB
                db.DBInsertUser(user);
            }

            return message;
        }
        else if (command.equals("/subscribe"))
        {
            if ((db.DBSelectUser(userId).getUserLatitude() != 0) && (db.DBSelectUser(userId).getUserLongitude() != 0))
            {
                message = "Now you have a subscription for daily weather forecast for your location. " +
                        "\nYou will receive it every day at 7 am." +
                        "\nTo change location share it and subscribe once again.";

                db.DBUpdateSubs(1, userId);
            }
            else
            {
                message = "Please share your location first, before subscribe.";
            }

            return message;
        }
        else if (command.equals("/unsubscribe"))
        {
            message = "You no longer have a subscription.";
            db.DBUpdateSubs(0, userId);

            return message;
        }
        else
        {
            return "Looks like this command is unknown.\nTry another command.";
        }
    }

    public static String parseLocation(Update update, Location location)
    {
        int userId = update.getMessage().getFrom().getId();
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        DBActions db = new DBActions();

        CurrentWeather currentWeather = JSON.createCurrent(Weather.currentWeather(latitude, longitude, key));
        DailyWeather dailyWeather = JSON.createDaily(Weather.dailyWeather(latitude, longitude, key));

        String message = WeatherInfo.currentInfo(currentWeather) + "\n\n" + WeatherInfo.dailyInfo(dailyWeather);

        db.DBUpdateUserLoc(userId, latitude, longitude);

        return message;
    }
}