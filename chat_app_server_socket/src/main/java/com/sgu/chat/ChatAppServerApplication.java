package com.sgu.chat;

import com.sgu.chat.socket_connection.SocketConnection;

public class ChatAppServerApplication {
        
    public static void main(String[] args) {
//        new APIConnection().getJWT();
//        CLITool cliTool = new CLITool();
//        cliTool.run();
        SocketConnection socket = new SocketConnection();
        socket.startConnection();
    }

}
