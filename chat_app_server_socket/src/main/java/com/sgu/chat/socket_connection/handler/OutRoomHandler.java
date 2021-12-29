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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class OutRoomHandler {
    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            String nickname = data.getString("nickname");

            Group group = new GetPairingHandler().getGroup(nickname);
            DataSocket dataSocket = new DataSocket();
            Map<String, Socket> userList = SocketConnection.socketClients;
            String dataSend;
            
            if (group != null){
                String user_1 = group.user_1;
                String user_2 = group.user_2;
                Socket socket_1 = userList.get(user_1);
                Socket socket_2 = userList.get(user_2);
                    
                dataSend = dataSocket.exportDataOutRoom();

                BufferedWriter out_socket_1 = new BufferedWriter(new OutputStreamWriter(socket_1.getOutputStream()));
                out_socket_1.write(dataSend);
                out_socket_1.newLine();
                out_socket_1.flush();

                BufferedWriter out_socket_2 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));
                out_socket_2.write(dataSend);
                out_socket_2.newLine();
                out_socket_2.flush();

                new GetPairingHandler().removeGroup(user_1);
                userList.remove(user_1);                
                userList.remove(user_2);


                Logging.log(Logging.MATCH_TYPE, "match_end", "[Out Room]: " + nickname);
            }
            
        } catch (IOException ex) {

        }
    }
}
