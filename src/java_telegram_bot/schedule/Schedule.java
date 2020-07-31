package java_telegram_bot.schedule;

import java_telegram_bot.database.DBActions;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.List;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import java_telegram_bot.bot.Bot;
import java_telegram_bot.bot.WeatherInfo;
import java_telegram_bot.models.User;
import java_telegram_bot.models.weather.DailyWeather;
import java_telegram_bot.owmApi.JSON;
import java_telegram_bot.owmApi.Weather;
import java_telegram_bot.security.Security;

public class Schedule implements Job
{
    String key = Security.getInstance().getApiKey();

    @Override
    public void execute(JobExecutionContext jobExecutionContext)
    {
        DBActions db = new DBActions();
        List<User> subscribers = db.SelectSubscribers();

        if (subscribers != null)
        {
            for (int i = 0; i < subscribers.size(); i++ )
            {
                if (subscribers.get(i).getUserLatitude() != 0.0 && subscribers.get(i).getUserLongitude() != 0.0)
                {
                    SendMessage message = new SendMessage();

                    DailyWeather dailyWeather = JSON.createDaily(Weather.dailyWeather(subscribers.get(i).getUserLatitude(), subscribers.get(i).getUserLongitude(), key));

                    String output = WeatherInfo.dailyInfo(dailyWeather);

                    message.setText(output);
                    message.setChatId(subscribers.get(i).getUserId());

                    AbsSender sender = new Bot();

                    try
                    {
                        sender.execute(message);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}