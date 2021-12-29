package com.sgu.chat.socket_connection.handler;

import com.sgu.chat.entity.Group;
import com.sgu.chat.logging.Logging;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class SendMessageHandler {
    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        DataSocket dataSocket = new DataSocket();
        String user = data.getString("nickname");       
        String message = data.getString("message");

        Group group = new GetPairingHandler().getGroup(user);
        if (group != null){
            
            Map<String, Socket> userList = SocketConnection.socketClients;
            Socket socketUser1 = userList.get(group.user_1);
            Socket socketUser2 = userList.get(group.user_2);
            String dataSend = dataSocket.exportDataSendMessage(user, message);
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
        }
    }
}
