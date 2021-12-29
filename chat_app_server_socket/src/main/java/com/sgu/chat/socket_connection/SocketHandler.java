package com.sgu.chat.socket_connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;
import org.json.JSONObject;

public abstract class SocketHandler {
    public void onHandle(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {}
}