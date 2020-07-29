package java_telegram_bot.models.weather;

public class CurrentWeather
{
    private CurrentInfo current;

    public class CurrentInfo
    {
        double temp;
        double feels_like;
        int pressure;
        int humidity;
        double wind_speed;
        double wind_deg;
        WeatherInfo[] weather;

        public double getTemp()
        {
            return temp;
        }

        public double getFeels_like()
        {
            return feels_like;
        }

        public int getPressure()
        {
            return pressure;
        }

        public int getHumidity()
        {
            return humidity;
        }

        public double getWind_speed()
        {
            return wind_speed;
        }

        public double getWind_deg()
        {
            return wind_deg;
        }

        public WeatherInfo[] getWeather()
        {
            return weather;
        }
    }

    public class WeatherInfo
    {
        String description;

        public String getDescription()
        {
            return description;
        }
    }

    public CurrentInfo getCurrent()
    {
        return current;
    }
}
