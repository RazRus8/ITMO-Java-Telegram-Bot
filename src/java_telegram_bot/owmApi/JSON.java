package java_telegram_bot.owmApi;

import com.google.gson.Gson;
import java_telegram_bot.models.weather.CurrentWeather;
import java_telegram_bot.models.weather.DailyWeather;

public class JSON
{
    // returns current weather model with data from json
    public static CurrentWeather createCurrent(String json)
    {
        try
        {
            // mapping json to CurrentWeather instance
            CurrentWeather currentWeather = new Gson().fromJson(json, CurrentWeather.class);
            return currentWeather;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // returns daily weather model with data from json
    public static DailyWeather createDaily(String json)
    {
        try
        {
            // mapping json to DailyWeather instance
            DailyWeather dailyWeather = new Gson().fromJson(json, DailyWeather.class);
            return dailyWeather;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
