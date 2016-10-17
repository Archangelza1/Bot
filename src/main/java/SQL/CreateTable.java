package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static Connection.BaseHandler.*;

/**
 * Created by Brendan TestHeroes on 2016/10/14.
 */
public class CreateTable {
    public static void createUserTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "	P_UserDBID INT NOT NULL UNIQUE,\n"
                + "	UserName VARCHAR(255) NOT NULL,\n"
                + " U_UserID INT NOT NULL, \n"
                + " Registered BIT NOT NULL DEFAULT '0', \n"
                + " Clan VARCHAR(255), \n"
                + " Active BIT NOT NULL DEFAULT '0', \n"
                + " PRIMARY KEY (P_UserDBID)\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createClanTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Clan (\n"
                + " P_ClanID INT NOT NULL UNIQUE, \n"
                + " ClanName VARCHAR (255) NOT NULL, \n"
                + " ClanNick VARCHAR (255), \n"
                + " P_UserDBID INT NOT NULL, \n"
                + " ClanSize INT NOT NULL DEFAULT '0', \n"
                + " ClanChannelID INT NOT NULL UNIQUE, \n"
                + " ActiveClan BIT NOT NULL DEFAULT '0', \n"
                + " PRIMARY KEY (P_ClanID), \n"
                + " FOREIGN KEY (P_UserDBID) REFERENCES Users (P_UserDBID) \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createChannelTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Channels ( \n"
                + " P_ChannelID INT NOT NULL UNIQUE, \n"
                + " ChannelName VARCHAR(255) NOT NULL, \n"
                + " P_UserDBID INT NOT NULL, \n"
                + " ChannelInfo VARCHAR(255), \n"
                + " PRIMARY KEY (P_ChannelID), \n"
                + " FOREIGN KEY (P_UserDBID) REFERENCES Users (P_UserDBID) \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
