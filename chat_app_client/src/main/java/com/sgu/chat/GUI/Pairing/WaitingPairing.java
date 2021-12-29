package com.sgu.chat.GUI.Pairing;

import com.sgu.chat.GUI.Login.ChatLogin;
import com.sgu.chat.InternalData.SessionData;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import com.sgu.chat.socket_connection.SocketHandler;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.*;
import org.json.JSONObject;

public class WaitingPairing extends javax.swing.JFrame {
    int x_Mouse, y_Mouse; // For Moving Window
    SocketConnection socket = new SocketConnection();
    DataSocket dataSocket = new DataSocket();

    public WaitingPairing() {
        initComponents();
        setSize(664, 439);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        waiting_pairing_panel.setBackground(new Color(0, 0, 0, 0));
        
        socket.addListenConnection("send_invitation", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                String nickname = data.getString("nickname");
                
                GetPairing getPairing = new GetPairing(nickname);
                getPairing.setVisible(true);
                dispose(); 
            }
        });
        
        
        socket.addListenConnection("out_waiting_response", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                ChatLogin chatLogin = new ChatLogin();
                chatLogin.setVisible(true);
                dispose(); 
            }
        });
        
        
        
        String nickname = SessionData.nickname;
        String data = dataSocket.exportDataWaitingPairing(nickname);
        socket.sendData(data);
    }
                         
    private void initComponents() {

        moving_window = new javax.swing.JLabel();
        waiting_pairing_button = new javax.swing.JLabel();
        waiting_pairing_panel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        moving_window.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moving_windowMouseDragged(evt);
            }
        });
        moving_window.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moving_windowMousePressed(evt);
            }
        });
        getContentPane().add(moving_window);
        moving_window.setBounds(0, 0, 660, 140);

        waiting_pairing_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-exit-button-hover.png"))); // NOI18N
        waiting_pairing_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                waiting_pairing_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                waiting_pairing_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                waiting_pairing_buttonMouseExited(evt);
            }
        });
        getContentPane().add(waiting_pairing_button);
        waiting_pairing_button.setBounds(200, 240, 268, 140);

        waiting_pairing_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-panel.png"))); // NOI18N
        getContentPane().add(waiting_pairing_panel);
        waiting_pairing_panel.setBounds(0, 0, 664, 439);

        pack();
    }                     

    private void moving_windowMousePressed(java.awt.event.MouseEvent evt) {                                           
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }                                          

    private void moving_windowMouseDragged(java.awt.event.MouseEvent evt) {                                           
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - x_Mouse, y - y_Mouse);
    }                                          

    private void waiting_pairing_buttonMouseEntered(java.awt.event.MouseEvent evt) {                                                    
        waiting_pairing_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-exit-button.png"))); // NOI18N
        waiting_pairing_button.setBounds(200, 240, 268, 140);
    }                                                   

    private void waiting_pairing_buttonMouseExited(java.awt.event.MouseEvent evt) {                                                   
        waiting_pairing_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-exit-button-hover.png"))); // NOI18N
        waiting_pairing_button.setBounds(200, 240, 268, 140);
    }                                                  

    private void waiting_pairing_buttonMouseClicked(java.awt.event.MouseEvent evt) { 
        String dataSend = dataSocket.exportDataOutWaiting(SessionData.nickname);
        socket.sendData(dataSend);
        
    }                                                   

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WaitingPairing().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel moving_window;
    private javax.swing.JLabel waiting_pairing_button;
    private javax.swing.JLabel waiting_pairing_panel;
}
