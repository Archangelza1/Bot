package SQL;

import java.sql.*;

import static Connection.BaseHandler.fileName;
import static Connection.BaseHandler.resourcePath;

/**
 * Created by Brendan TestHeroes on 2016/10/14.
 */
public class InsertChannel {
    public void ChannelInsert(int P_ChannelID, String ChannelName, int P_UserDBID, String ChannelInfo) {
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        String sql = "INSERT INTO Channels(P_ChannelID,ChannelName,P_UserDBID,ChannelInfo) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, P_ChannelID);
            pstm.setString(2, ChannelName);
            pstm.setInt(3, P_UserDBID);
            pstm.setString(4, ChannelInfo);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
