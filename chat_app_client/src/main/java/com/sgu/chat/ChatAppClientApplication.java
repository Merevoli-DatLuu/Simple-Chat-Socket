package com.sgu.chat;

import com.sgu.chat.GUI.Login.ChatLogin;
import com.sgu.chat.socket_connection.SocketConnection;
import java.io.IOException;

public class ChatAppClientApplication {

    public static void main(String[] args) {
        SocketConnection socket = new SocketConnection();
        socket.startConnection();
        new ChatLogin().setVisible(true);
    }
}
