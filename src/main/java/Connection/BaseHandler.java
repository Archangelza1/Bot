package Connection;

import Chat.ChatHandler;
import SQL.*;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;

import java.util.logging.Level;

/**
 * Created by Brendan TestHeroes on 2016/10/11.
 */
public class BaseHandler {

    public static final String resourcePath = "src//main//resources//";
    public static final String fileName = "Bot.db";
    public static final TS3Config config = new TS3Config();
    public static final TS3Query query = new TS3Query();
    public static final TS3Api api = query.getApi();
    public static final TS3ApiAsync apiAsync = query.getAsyncApi();

    public static void main(String[] args) {
        //Setting IP For Query To Telnet To
        config.setHost("localhost");
        config.setDebugLevel(Level.ALL);

        //Connect Query
        query.connect();

        //Login Query
        api.login("serveradmin", "DOturWs1");
        api.selectVirtualServerById(2);
        api.setNickname("-=NaF=- Bot");
        api.sendServerMessage("-=NaF=- Bot Is Online!");

        ConnectDatabase.connect();
        ChatHandler.ChannelListener(1);
    }
}