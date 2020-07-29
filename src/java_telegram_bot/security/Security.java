package java_telegram_bot.security;

// Singleton pattern
public class Security
{
    private String apiKey;
    private String botUserName;
    private String botToken;
    private String hostName;
    private String databaseName;
    private static Security instance = null;

    private Security() {}

    private Security(String apiKey, String botUserName, String botToken, String hostName, String databaseName)
    {
        this.apiKey = apiKey;
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.hostName = hostName;
        this.databaseName = databaseName;
    }

    public static Security getInstance()
    {
        if (instance != null)
        {
            return instance;
        }

        return null;
    }

    public static Security createInstance(String apiKey, String botUserName, String botToken, String hostName, String databaseName)
    {
        if (instance == null)
        {
            instance = new Security(apiKey, botUserName, botToken, hostName, databaseName);
        }

        return instance;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public String getBotUserName()
    {
        return botUserName;
    }

    public String getBotToken()
    {
        return botToken;
    }

    public String getHostName()
    {
        return hostName;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }
}
