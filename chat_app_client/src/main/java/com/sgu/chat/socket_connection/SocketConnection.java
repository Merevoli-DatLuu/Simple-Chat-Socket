package com.sgu.chat.socket_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


/**
 * 
 * @attr state: Danh sách trạng thái của các handler
 * @attr events: Danh sách thread xử lý các handler
 * @method SocketConnection(): Khởi chạy socket connection
 * @method stopConnection(): Dừng socket connection
 * @method listenConnectionBase(): Xử lý handler (hàm base) 
 * @method listenConnection(): khởi chạy handler event (lưu ý: override lại SocketHandler)
 * @method stopEvent(): dừng event cụ thể
 * @method getState(): Lấy danh sách trại thái của các handler
 */
public class SocketConnection {
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;
    private static String socketHost = "localhost";    // IP của socket server 
    private static int socketPort = 5000;                   // PORT của socket server 
    private static Map <String, SocketHandler> actions = new HashMap<String, SocketHandler>();
    private static ArrayList<Thread> events = new ArrayList<Thread>(); // Unused

    public SocketConnection() {}
    
    public void startConnection(){
        try {
            socket = new Socket(socketHost, socketPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("===== Connected to server =====");
            
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleServer(socket, in, out);
                }
            });  
            thread.start();
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void handleServer(Socket socket, BufferedReader in, BufferedWriter out){
        try {
            DataSocket dataSocket = new DataSocket();
            while (true){
                String rawDateReceive = in.readLine();
                
                if (rawDateReceive == null){
                    continue;
                }
                
                System.out.println("Receive" + rawDateReceive);
                JSONObject dataReceive = dataSocket.importData(rawDateReceive);
                JSONObject data = dataReceive.getJSONObject("data");
                String type = dataReceive.getString("type");

                switch (type) {
                    case "send_message":
                        System.out.println("send_message");
                        actions.get("send_message").onHandle(data, in, out);
                        break;
                    case "send_invitation":
                        System.out.println("send_invitation");
                        actions.get("send_invitation").onHandle(data, in, out);
                        break;
                    case "send_nickname_response":
                        System.out.println("send_nickname_response");
                        actions.get("send_nickname_response").onHandle(data, in, out);
                        break;
                    case "start_message":
                        System.out.println("start_message");
                        actions.get("start_message").onHandle(data, in, out);
                        break;
                    case "out_room_response":
                        System.out.println("out_room_response");
                        actions.get("out_room_response").onHandle(data, in, out);
                        break;
                    case "exit_app_response":
                        System.out.println("exit_app_response");
                        actions.get("exit_app_response").onHandle(data, in, out);
                        break;
                    case "out_waiting_response":
                        System.out.println("out_waiting_response");
                        actions.get("out_waiting_response").onHandle(data, in, out);
                        break;
                    case "stop":
                        System.out.println("July");
                        in.close();
                        out.close();
                        socket.close();
                        break;
                }
            }
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void addListenConnection(String actionID, SocketHandler handler){
        actions.put(actionID, handler);
    }
            
    public void stopConnection(){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("===== Closed connection to server =====");
        } catch (IOException e) { System.err.println(e); }
    }
    
    
        
    public void sendData(String data){
        try {
            out.write(data);
            out.newLine();
            out.flush();
        } catch (IOException e) { System.err.println(e); }
    }
    
    public static void main(String[] args) {}
}
