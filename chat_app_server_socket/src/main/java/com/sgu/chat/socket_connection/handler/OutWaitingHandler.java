package com.sgu.chat.socket_connection.handler;

import com.sgu.chat.entity.Group;
import com.sgu.chat.logging.Logging;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import static com.sgu.chat.socket_connection.SocketConnection.socketClients;
import static com.sgu.chat.socket_connection.handler.WaitingPairingHandler.denyUsers;
import static com.sgu.chat.socket_connection.handler.WaitingPairingHandler.userQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class OutWaitingHandler {
    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            String nickname = data.getString("nickname");

            WaitingPairingHandler.userQueue.remove(nickname);
            if (socketClients.containsKey(nickname)){
                socketClients.remove(nickname);
            }
            denyUsers.put(nickname, new ArrayList<>());
            DataSocket dataSocket = new DataSocket();
            String dataSend = dataSocket.exportDataOutWaiting();
            out.write(dataSend);
            out.newLine();
            out.flush();
            
        } catch (IOException ex) {

        }
    }
}
