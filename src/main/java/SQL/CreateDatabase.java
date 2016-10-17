package SQL;

import java.sql.*;

import static Connection.BaseHandler.fileName;
import static Connection.BaseHandler.resourcePath;

/**
 * Created by Brendan TestHeroes on 2016/10/13.
 */
public class CreateDatabase {
    public static void createNewDatabase() {

        String url = "jdbc:sqlite:" + resourcePath + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
