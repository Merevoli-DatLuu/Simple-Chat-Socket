package com.sgu.chat.socket_connection;

import com.sgu.chat.socket_connection.handler.SendMessageHandler;
import com.sgu.chat.socket_connection.handler.SendNicknameHandler;
import com.sgu.chat.socket_connection.handler.WaitingPairingHandler;;
import com.sgu.chat.socket_connection.handler.GetPairingHandler;
import com.sgu.chat.socket_connection.handler.OutRoomHandler;
import com.sgu.chat.socket_connection.handler.OutWaitingHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import com.sgu.chat.logging.Logging;

public class SocketConnection {

    private static ServerSocket server = null;
    private static String socketHost = "localhost";
    private static int socketPort = 5000;
    public static Map<String, Socket> socketClients = new HashMap<String, Socket>(); 
    
    public SocketConnection() {
    }

    public void startConnection() {
        try {
            server = new ServerSocket(socketPort);
            System.out.println("===== Socket server has started =====");
            Logging.log(Logging.SOCKET_TYPE, "socket_start", "===== Socket server has started =====");

            Thread thread_get_pairing = new Thread(new Runnable() {
                @Override
                public void run() {
                    new WaitingPairingHandler().getPair();
                }
            });
            thread_get_pairing.start();
            
            while (true) {
                Socket socket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Logging.log(Logging.SOCKET_TYPE, "user_access", "user " + socket + " accessed");
                
                
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleClient(socket, in, out);
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
//            System.err.println(e);
        }
    }

    public void handleClient(Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            DataSocket dataSocket = new DataSocket();
            boolean is_running = true;
            while (is_running) {
                String rawDateReceive = in.readLine();
                
                if (rawDateReceive == null || rawDateReceive.trim().equals("")){
                    continue;
                }
                
                System.out.println(rawDateReceive);
                
                Logging.log(Logging.SOCKET_TYPE, "socket_received", "Received: " + rawDateReceive);
                JSONObject dataReceive = dataSocket.importData(rawDateReceive);
                JSONObject data = dataReceive.getJSONObject("data");
                String type = dataReceive.getString("type");

                switch (type) {
                    case "send_nickname":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "send_nickname");
                        new SendNicknameHandler().run(data, socket, in, out);
                        break;
                    case "waiting_pairing":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "waiting_pairing");
                        new WaitingPairingHandler().run(data, socket, in, out);
                        break;
                    case "get_pairing":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "get_pairing");
                        new GetPairingHandler().run(data, socket, in, out);
                        break;
                    case "send_message":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "send_message");
                        new SendMessageHandler().run(data, socket, in, out);
                        break;                    
                    case "out_room":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "out_room");
                        new OutRoomHandler().run(data, socket, in, out);
                        break;
                    case "out_waiting":
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "out_waiting");
                        new OutWaitingHandler().run(data, socket, in, out);
                        break;
                    case "exit_app":
                        String nickname = data.getString("nickname");
                        if (!nickname.equals("") && socketClients.containsKey(nickname)){
                            socketClients.remove(nickname);
                        }
                        String dataSend = dataSocket.exportDataExitApp();
                        out.write(dataSend);
                        out.newLine();
                        out.flush();
                        
                        in.close();
                        out.close();
                        socket.close();
                        is_running = false;
                        Logging.log(Logging.SOCKET_TYPE, "socket_type", "exit_app");
                        break;
                }
            }
        } catch (IOException e) {
            Logging.log(Logging.SOCKET_TYPE, "user_disconnect", "");
            socketClients.remove("");
//            System.err.println(e);
        }
    }

    public void stopConnection() {
        try {
            server.close();
            Logging.log(Logging.SOCKET_TYPE, "socket_close", "===== Closed socket =====");
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void updateSocketClients() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<String, Socket> userList = new SocketConnection().getSocketClients();
                    System.out.println(userList.size());

                    for (Map.Entry<String, Socket> e : userList.entrySet()) {
                        Socket socketClient = e.getValue();
                        if (socketClient.isClosed()) {
                            socketClients.remove(e.getKey());
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        thread.start();
    }

    public static Map<String, Socket> getSocketClients() {
        return socketClients;
    }

    public static void main(String[] args) {
    }
}
