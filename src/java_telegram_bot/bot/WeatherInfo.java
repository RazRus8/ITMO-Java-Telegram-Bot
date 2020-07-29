package java_telegram_bot.bot;

import java_telegram_bot.models.weather.*;
import java.text.DecimalFormat;

public class WeatherInfo
{
    private static StringBuilder str = new StringBuilder();
    private static DecimalFormat df = new DecimalFormat("#");

    // transforms degrees to string
    private static String windDirection(double degrees)
    {
        if ((degrees > 348.75 && degrees <= 360) || (degrees >= 0 && degrees <= 11.25))
        {
            return "North wind";
        }
        else if (degrees > 11.25 && degrees <= 33.75)
        {
            return "North-north-east wind";
        }
        else if (degrees > 33.75 && degrees <= 56.25)
        {
            return "North-east wind";
        }
        else if (degrees > 56.25 && degrees <= 78.75)
        {
            return "East-north-east wind";
        }
        else if (degrees > 78.75 && degrees <= 101.25)
        {
            return "East wind";
        }
        else if (degrees > 101.25 && degrees <= 123.75)
        {
            return "East-south-east wind";
        }
        else if (degrees > 123.75 && degrees <= 146.25)
        {
            return "South-east wind";
        }
        else if (degrees > 146.25 && degrees <= 168.75)
        {
            return "South-south-east wind";
        }
        else if (degrees > 168.75 && degrees <= 191.25)
        {
            return "South wind";
        }
        else if (degrees > 191.25 && degrees <= 213.75)
        {
            return "South-south-west wind";
        }
        else if (degrees > 213.75 && degrees <= 236.25)
        {
            return "South-west wind";
        }
        else if (degrees > 236.25 && degrees <= 258.75)
        {
            return "West-south-west wind";
        }
        else if (degrees > 258.75 && degrees <= 281.25)
        {
            return "West wind";
        }
        else if (degrees > 281.25 && degrees <= 303.75)
        {
            return "West-north-west wind";
        }
        else if (degrees > 303.75 && degrees <= 326.25)
        {
            return "North-west wind";
        }
        else if (degrees > 326.25 && degrees <= 348.75)
        {
            return "North-north-west wind";
        }
        else
        {
            return "";
        }
    }

    // Converts atmospheric pressure from mbar to mm Hg
    private static int atmosphericPressureConverter(int pressure)
    {
        pressure *= 0.7501;

        return pressure;
    }

    //returns format string to display current weather forecast for a user
    public static String currentInfo(CurrentWeather weather)
    {
        // clearing str
        str.setLength(0);

        str.append("Currently ").append(weather.getCurrent().getWeather()[0].getDescription()).append(".\n");
        str.append("Temperature ").append(df.format(weather.getCurrent().getTemp())).append("\u2103, feels like ").append(df.format(weather.getCurrent().getFeels_like())).append("\u2103.\n");
        str.append("Pressure ").append(atmosphericPressureConverter(weather.getCurrent().getPressure())).append(" mm Hg. \n");
        str.append("Humidity ").append(weather.getCurrent().getHumidity()).append("%.\n");
        str.append(windDirection(weather.getCurrent().getWind_deg())).append(", wind speed ").append(weather.getCurrent().getWind_speed()).append(" m/s.");

        return str.toString();
    }

    //returns format string to display a weather forecast for today and tomorrow for a user
    public static String dailyInfo(DailyWeather weather)
    {
        // clearing str
        str.setLength(0);

        str.append("Today ").append(weather.getDaily()[0].getWeather()[0].getDescription()).append(".\n");
        str.append("At morning ").append(df.format(weather.getDaily()[0].getTemp().getMorn())).append("\u2103.\n");
        str.append("At day ").append(df.format(weather.getDaily()[0].getTemp().getDay())).append("\u2103.\n");
        str.append("At evening ").append(df.format(weather.getDaily()[0].getTemp().getEve())).append("\u2103.\n");
        str.append("At night ").append(df.format(weather.getDaily()[0].getTemp().getNight())).append("\u2103.\n");
        str.append("Pressure ").append(atmosphericPressureConverter(weather.getDaily()[0].getPressure())).append(" mm Hg.\n");
        str.append("Humidity ").append(weather.getDaily()[0].getHumidity()).append("%.\n");
        str.append(windDirection(weather.getDaily()[0].getWind_deg())).append(",\nwind speed ").append(weather.getDaily()[0].getWind_speed()).append(" m/s.");
        str.append("\n\n");
        str.append("Tomorrow ").append(weather.getDaily()[1].getWeather()[0].getDescription()).append(".\n");
        str.append("At morning ").append(df.format(weather.getDaily()[1].getTemp().getMorn())).append("\u2103.\n");
        str.append("At day ").append(df.format(weather.getDaily()[1].getTemp().getDay())).append("\u2103.\n");
        str.append("At evening ").append(df.format(weather.getDaily()[1].getTemp().getEve())).append("\u2103.\n");
        str.append("At night ").append(df.format(weather.getDaily()[1].getTemp().getNight())).append("\u2103.\n");
        str.append("Pressure ").append(atmosphericPressureConverter(weather.getDaily()[1].getPressure())).append(" mm Hg.\n");
        str.append("Humidity ").append(weather.getDaily()[1].getHumidity()).append("%.\n");
        str.append(windDirection(weather.getDaily()[1].getWind_deg())).append(",\nwind speed ").append(weather.getDaily()[1].getWind_speed()).append(" m/s.");

        return str.toString();
    }
}
