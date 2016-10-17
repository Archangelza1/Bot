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
public class InsertClan {
    public void ClanInsert(int P_ClanID, String ClanName, String ClanNick, int P_UserDBID, int ClanSize, int ClanChannelID, boolean ActiveClan) {
        String url = "jdbc:sqlite:" + resourcePath + fileName;

        String sql = "INSERT INTO Clan(P_ClanID ,ClanName ,ClanNick ,P_UserDBID ,ClanSize ,ClanChannelID ,ActiveClan) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, P_ClanID);
            pstm.setString(2, ClanName);
            pstm.setString(3, ClanNick);
            pstm.setInt(4, P_UserDBID);
            pstm.setInt(5, ClanSize);
            pstm.setInt(6, ClanChannelID);
            pstm.setBoolean(7, ActiveClan);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
