package com.sgu.chat.socket_connection.handler;

import com.sgu.chat.entity.Group;
import com.sgu.chat.entity.User;
import com.sgu.chat.logging.Logging;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class GetPairingHandler {
    private static DataSocket datasocket = new DataSocket();
    static public ArrayList<Group> groups = new ArrayList<>();

    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {

        String nickname = data.getString("nickname");
        boolean is_accepted = data.getBoolean("is_accepted");
        Group group = getGroup(nickname);
        
        if (group == null){
            return ;
        }
        
        String dataSend;
        boolean is_success = false;
        if (is_accepted) {
            group.setAccept_pairing_1(nickname, true);
            group.setAccept_pairing_2(nickname, true);

            if (group.accept_pairing_1 && group.accept_pairing_2) {
                dataSend = datasocket.exportDataStartMessage(true);
                is_success = true;
            } else {
                return;
            }
        } else {
            dataSend = datasocket.exportDataStartMessage(false);
            removeGroup(nickname);
        }
        
        Map<String, Socket> userList = SocketConnection.socketClients;
        Socket socketUser1 = userList.get(group.user_1);
        Socket socketUser2 = userList.get(group.user_2);

        try {
            BufferedWriter outUser1 = new BufferedWriter(new OutputStreamWriter(socketUser1.getOutputStream()));
            BufferedWriter outUser2 = new BufferedWriter(new OutputStreamWriter(socketUser2.getOutputStream()));
            Logging.log(Logging.SOCKET_TYPE, "socket_send", "Send: " + dataSend);
            outUser1.write(dataSend);
            outUser1.newLine();
            outUser1.flush();

            outUser2.write(dataSend);
            outUser2.newLine();
            outUser2.flush();

        } catch (IOException ex) {
        
        }
        
        if (is_success){

            
            WaitingPairingHandler.denyUsers.put(group.user_1, new ArrayList<>());
            WaitingPairingHandler.denyUsers.put(group.user_2, new ArrayList<>());
        }
        else{
            ArrayList <String> denyUsers1 = WaitingPairingHandler.denyUsers.get(group.user_1);
            ArrayList <String> denyUsers2 = WaitingPairingHandler.denyUsers.get(group.user_2);
            
            denyUsers1.add(group.user_2);
            denyUsers2.add(group.user_1);
            
            WaitingPairingHandler.denyUsers.put(group.user_1, denyUsers1);
            WaitingPairingHandler.denyUsers.put(group.user_2, denyUsers2);
        }
        
        
    }

    public Group getGroup(String nickname) {
        for (Group g : groups) {
            if (g.user_1.equals(nickname) || g.user_2.equals(nickname)) {
                return g;
            }
        }
        return null;
    }

    public void addGroup(String user1, String user2) {
        Group group = new Group(user1, user2);
        groups.add(group);
    }
    
    public void removeGroup(String nickname){
        for (Group g : groups) {
            if (g.user_1.equals(nickname) || g.user_2.equals(nickname)) {
                groups.remove(g);
                break;
            }
        }
    }
}
