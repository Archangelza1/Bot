package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Connection.BaseHandler.fileName;
import static Connection.BaseHandler.resourcePath;

/**
 * Created by Brendan TestHeroes on 2016/10/14.
 */
public class InsertUser {
    public void UserInsert(int P_UserDBID, String UserName, int U_UserID, boolean Registered, String Clan, boolean Active) {
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        String sql = "INSERT INTO Users(P_UserDBID, UserName, U_UserID, Registered, Clan,Active) VALUES(?,?,?,?,?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, P_UserDBID);
            pstm.setString(2, UserName);
            pstm.setInt(3, U_UserID);
            pstm.setBoolean(4, Registered);
            pstm.setString(5, Clan);
            pstm.setBoolean(6, Active);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
