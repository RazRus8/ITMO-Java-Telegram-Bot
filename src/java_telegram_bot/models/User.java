package java_telegram_bot.models;

public class User
{
    private int userId;
    private int isBot;
    private String firstname;
    private String lastname;
    private String username;
    private int hasSubscription;
    private String addedToDb;
    private double userLatitude;
    private double userLongitude;

    public User()
    {

    }

    // For telegram isBot = boolean
    public User(int userId, boolean isBot, String firstname, String lastname, String username, int hasSubscription, double userLatitude, double userLongitude)
    {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.hasSubscription = hasSubscription;
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;

        if (isBot)
        {
            this.isBot = 1;
        }
        else
        {
            this.isBot = 0;
        }
    }

    // For database isBot = int
    public User(int userId, int isBot, String firstname, String lastname, String username, int hasSubscription, double userLatitude, double userLongitude)
    {
        this.userId = userId;
        this.isBot = isBot;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.hasSubscription = hasSubscription;
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getIsBot()
    {
        return isBot;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public String getUsername()
    {
        return username;
    }

    public int getHasSubscription()
    {
        return hasSubscription;
    }

    public String getAddedToDb()
    {
        return addedToDb;
    }

    public void setAddedToDb(String addedToDb)
    {
        this.addedToDb = addedToDb;
    }

    public double getUserLatitude()
    {
        return userLatitude;
    }

    public void setUserLatitude(double userLatitude)
    {
        this.userLatitude = userLatitude;
    }

    public double getUserLongitude()
    {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude)
    {
        this.userLongitude = userLongitude;
    }
}
