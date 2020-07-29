package java_telegram_bot.database;

import java.sql.*;

import java_telegram_bot.Info;
import java_telegram_bot.models.User;
import java_telegram_bot.security.Security;

public class DBActions extends Thread
{
    // Thread
    int threadId;
    static int t_nextId = 1;

    // connection string
    private String connectionUrl = "jdbc:sqlserver:" + Security.getInstance().getHostName() + ";databaseName=" + Security.getInstance().getDatabaseName() + ";integratedSecurity=true";
    private StringBuilder str = new StringBuilder();

    // select specific user from data base without location
    public User DBSelectUser(int userId)
    {
        User user = null;

        // clearing str
        str.setLength(0);

        str.append("select * from users where userId = ").append(userId).append(";");

        // command string
        String command = str.toString();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl); Statement statement = connection.createStatement())
        {
            resultSet = statement.executeQuery(command);

            while (resultSet.next())
            {
                user = new User(Integer.parseInt(resultSet.getString(2)),
                        Integer.parseInt(resultSet.getString(3)),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        Integer.parseInt(resultSet.getString(7)),
                        Double.parseDouble(resultSet.getString(9)),
                        Double.parseDouble(resultSet.getString(10)));
            }

            System.out.println("User successfully red from the database.");

            return user;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // insert new user to database
    public void DBInsertUser(User user)
    {
        // clearing str
        str.setLength(0);

        str.append("insert into [dbo].[users] ([userId], [isBot], [firstname], [lastname], [username], [hasSubscription], [addedToDb], [userLatitude], [userLongitude]) values (");
        str.append(user.getUserId()).append(", ");
        str.append(user.getIsBot()).append(", '");
        str.append(user.getFirstname()).append("', '");
        str.append(user.getLastname()).append("', '");
        str.append(user.getUsername()).append("', ");
        str.append(user.getHasSubscription()).append(", ");
        str.append("getdate()").append(", ");
        str.append(user.getUserLatitude()).append(", ");
        str.append(user.getUserLongitude()).append(");");

        // command string
        String command = str.toString();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl); PreparedStatement statement = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS))
        {
            statement.execute();
            resultSet = statement.getGeneratedKeys();

            while (resultSet.next())
            {
                System.out.println("Generated: " + resultSet.getString(1));
            }

            System.out.println("User successfully added to the database.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // insert user's location
    public void DBUpdateUserLoc(int userId, double latitude, double longitude)
    {
        // clearing str
        str.setLength(0);

        str.append("update [dbo].[users] ");
        str.append("set [userLatitude] = ").append(latitude);
        str.append(", [userLongitude] = ").append(longitude);
        str.append(" where userId = ").append(userId).append(";");

        // command string
        String command = str.toString();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl); PreparedStatement statement = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS))
        {
            statement.execute();
            resultSet = statement.getGeneratedKeys();

            while (resultSet.next())
            {
                System.out.println("Generated: " + resultSet.getString(1));
            }

            System.out.println("User's location successfully added to the database.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // update user's subscription plan
    public void DBUpdateSubs(int hasSubscription, int userId)
    {
        // clearing str
        str.setLength(0);

        str.append("update [dbo].[users] ");
        str.append("set [hasSubscription] = ").append(hasSubscription);
        str.append(" where userId = ").append(userId).append(";");

        // command string
        String command = str.toString();

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl); PreparedStatement statement = connection.prepareStatement(command, Statement.RETURN_GENERATED_KEYS))
        {
            statement.execute();
            resultSet = statement.getGeneratedKeys();

            while (resultSet.next())
            {
                System.out.println("Generated: " + resultSet.getString(1));
            }

            System.out.println("User has updated successfully his subscription.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    // Multithreading
    synchronized static int getT_nextId()
    {
        return t_nextId++;
    }

    public void run(int i)
    {

    }
}