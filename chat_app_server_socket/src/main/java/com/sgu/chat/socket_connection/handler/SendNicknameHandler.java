package com.sgu.chat.socket_connection.handler;

import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SendNicknameHandler {

    public static DataSocket dataSocket = new DataSocket();

    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            String nickname = data.getString("nickname");
            
            Map<String, Socket> socketClients = SocketConnection.socketClients;
            
            String dataSend = "";
            if (socketClients.keySet().contains(nickname)) {
                dataSend = dataSocket.exportDataSendNickname(false, "This nickname has been taken");
            }
            else {
                socketClients.put(nickname, socket);
                dataSend = dataSocket.exportDataSendNickname(true, "");
            }
            
            out.write(dataSend);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(SendNicknameHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
