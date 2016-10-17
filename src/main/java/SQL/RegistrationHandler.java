package SQL;


import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Connection.BaseHandler.api;
import static Connection.BaseHandler.fileName;
import static Connection.BaseHandler.resourcePath;


/**
 * Created by Brendan TestHeroes on 2016/10/17.
 */
public class RegistrationHandler {

    public static int InvokerID;
    public static int clientId = api.whoAmI().getId();

    public static void RegisterListener() {


        api.registerAllEvents();
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent ChatHandler) {
                InvokerID = ChatHandler.getInvokerId();
                String RegisterClan = ChatHandler.getMessage();
                String Check = RegisterClan.toLowerCase();
                if ((ChatHandler.getTargetMode() == TextMessageTargetMode.CHANNEL) &&
                        (ChatHandler.getInvokerId() != clientId) && (ChatHandler.getInvokerId() == InvokerID) &&
                        Check.contains("!register")) {

                }
            }
        });
    }

    public void channelupdate(int id, String name, double capacity) {
        String url = "jdbc:sqlite:" + resourcePath + fileName;
        String sql = "UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            // set the corresponding param


            // update
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
