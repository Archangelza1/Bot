package SQL;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static Connection.BaseHandler.*;

/**
 * Created by Brendan TestHeroes on 2016/10/14.
 */
public class ConnectDatabase {
    public static void connect() {
        File dbfile = new File(resourcePath + fileName);
        if(dbfile.exists()) {
            System.out.println("The SQL Does Exist");
            Connection conn = null;
            try {
                // db parameters
                String url = "jdbc:sqlite:" + resourcePath + fileName;
                conn = DriverManager.getConnection(url);

                System.out.println("Connection to SQLite has been established.");

                CreateTable.createUserTable();
                CreateTable.createClanTable();
                CreateTable.createChannelTable();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            CreateDatabase.createNewDatabase();
            connect();
        }
    }
}
