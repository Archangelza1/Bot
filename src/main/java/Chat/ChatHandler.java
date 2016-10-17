package Chat;

import SQL.InsertChannel;
import SQL.InsertClan;
import SQL.InsertUser;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ChannelCreateEvent;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.VirtualServerInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static Connection.BaseHandler.api;

/**
 * Created by Brendan TestHeroes on 2016/10/11.
 */
public class ChatHandler {
    static final HashMap<ChannelProperty, String> properties = new HashMap<>();
    static ClientInfo clientdetails;
    static VirtualServerInfo clanprops;

    public static int clientId = api.whoAmI().getId();
    public static int PersonelId;
    public static String FinalName;
    public static int InvokerID;
    public static int ClientDBID;
    public static int ChanId;
    public static ChannelInfo info;
    public static String ChannelName;
    public static String Username;
    public static boolean ActiveUser = true;

    public static void ChannelListener(final int ChannelID) {


        api.registerAllEvents();
        api.addTS3Listeners(new TS3EventAdapter() {

            @Override
            public void onTextMessage(TextMessageEvent ChatHandler) {
                String RegisterClan = ChatHandler.getMessage();
                String Check = RegisterClan.toLowerCase();
                InvokerID = ChatHandler.getInvokerId();
                clientdetails = api.getClientInfo(InvokerID);

                if ((ChatHandler.getTargetMode() == TextMessageTargetMode.CHANNEL) &&
                        (ChatHandler.getInvokerId() != clientId) && (ChatHandler.getInvokerId() == InvokerID) &&
                        Check.contains("!register")) {
                            String[] ClanName = RegisterClan.split(" ");
                            ClanName[0] = "";
                            PersonelId = ChatHandler.getInvokerId();
                            properties.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                            int defaultChannelId = api.whoAmI().getChannelId();
                            properties.put(ChannelProperty.CPID, String.valueOf(defaultChannelId));
                            FinalName = Arrays.toString(ClanName);
                            for(int i = 0; i < ClanName.length; i++) {
                                FinalName = FinalName.substring(1, FinalName.length()-1).replaceAll(",", "");
                                api.createChannel(FinalName, properties);
                                break;
                            }
                }
            }

            @Override
            public void onClientJoin(ClientJoinEvent Join) {
                InsertUser user = new InsertUser();
                int NewClientID = Join.getClientId();
                api.sendPrivateMessage(NewClientID, "Type !Register <Clan Name> To Get A Channel");
                user.UserInsert(Join.getClientDatabaseId(), Join.getClientNickname(),Join.getClientId(),false,"",true);
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent CreateSuccess) {
                InsertChannel channel = new InsertChannel();
                InsertClan clan = new InsertClan();
                api.pokeClient(PersonelId, "Channel Made Successfully, Moving You There");
                clientdetails = api.getClientInfo(InvokerID);
                ClientDBID = clientdetails.getDatabaseId();
                ChanId = CreateSuccess.getChannelId();
                info = api.getChannelInfo(ChanId);
                ChannelName = info.getName();
                api.moveClient(PersonelId, ChanId);
                clanprops = api.getServerInfo();
                api.setClientChannelGroup(9 ,ChanId, ClientDBID);
                Map<String, String> test = info.getMap();
                clan.ClanInsert(ChanId,ChannelName,"",ClientDBID,0,ChanId,true);
                channel.ChannelInsert(ChanId,ChannelName,ClientDBID, String.valueOf(test));
            }
        });
    }
}
