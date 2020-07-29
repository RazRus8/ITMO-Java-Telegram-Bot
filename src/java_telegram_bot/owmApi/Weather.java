package java_telegram_bot.owmApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather
{
    public static String currentWeather(double latitude, double longitude, String key)
    {
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&exclude=minutely,hourly,daily&units=metric&appid=" + key;

        try
        {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer content = new StringBuffer();

            while ((input = in.readLine()) != null)
            {
                content.append(input);
            }

            in.close();
            connection.disconnect();

            System.out.println("Json file with current forecast for city successfully loaded from server. Response code: " + status);

            return content.toString();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static String dailyWeather(double latitude, double longitude, String key)
    {
        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=" + latitude + "&lon=" + longitude + "&exclude=current,minutely,hourly&units=metric&appid=" + key;

        try
        {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            int status = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            StringBuffer content = new StringBuffer();

            while ((input = in.readLine()) != null)
            {
                content.append(input);
            }

            in.close();
            connection.disconnect();

            System.out.println("Json file with daily forecast for city successfully loaded from server. Response code: " + status);

            return content.toString();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
