package java_telegram_bot.models.weather;

public class DailyWeather
{
    private DailyInfo[] daily;

    public class DailyInfo
    {
        TempInfo temp;
        int pressure;
        int humidity;
        double wind_speed;
        double wind_deg;
        WeatherInfo[] weather;

        public TempInfo getTemp()
        {
            return temp;
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

    public class TempInfo
    {
        double morn;
        double day;
        double eve;
        double night;

        public double getMorn()
        {
            return morn;
        }

        public double getDay()
        {
            return day;
        }

        public double getEve()
        {
            return eve;
        }

        public double getNight()
        {
            return night;
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

    public DailyInfo[] getDaily()
    {
        return daily;
    }
}
