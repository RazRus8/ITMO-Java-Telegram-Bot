package java_telegram_bot;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java_telegram_bot.bot.Bot;
import java_telegram_bot.security.Security;
import java_telegram_bot.schedule.Schedule;

public class Main
{
    public static void main(String[] args) throws SchedulerException
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

        JobDetail job = JobBuilder.newJob(Schedule.class).build();

        // make trigger to send daily weather forecast for subscribers every day at 7:00am
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 7 1/1 * ? *"))
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}