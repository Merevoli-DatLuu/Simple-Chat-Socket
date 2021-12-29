package com.sgu.chat.GUI.Pairing;

import com.sgu.chat.GUI.Login.ChatLogin;
import com.sgu.chat.GUI.MainScreen.MainScreen;
import com.sgu.chat.GUI.MainScreen.MainScreen;
import com.sgu.chat.InternalData.SessionData;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import com.sgu.chat.socket_connection.SocketHandler;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import org.json.JSONObject;

public class GetPairing extends javax.swing.JFrame {
    int x_Mouse, y_Mouse; // For Moving Window
    SocketConnection socket = new SocketConnection();
    DataSocket dataSocket = new DataSocket();

    public GetPairing(String nickname) {
        initComponents(nickname);
        setSize(885, 586);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        get_pairing_panel.setBackground(new Color(0, 0, 0, 0));
        get_pairing_title.setText(nickname);
        
        socket.addListenConnection("start_message", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                boolean is_started = data.getBoolean("is_started");
                
                if (is_started){
                    get_pairing_status_false.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-status-true.png"))); // NOI18N
        
                    MainScreen mainScreen = new MainScreen(nickname);
                    mainScreen.setVisible(true);
                    dispose(); 
                }
                else{
                    WaitingPairing waitingPairing = new WaitingPairing();
                    waitingPairing.setVisible(true);
                    dispose(); 
                }
            }
        });
        
    }

    private void initComponents(String nickname) {
        moving_window = new javax.swing.JLabel();
        get_pairing_title = new javax.swing.JLabel();
        get_pairing_status_true = new javax.swing.JLabel();
        get_pairing_status_false = new javax.swing.JLabel();
        get_pairing_nickname_1 = new javax.swing.JLabel();
        get_pairing_nickname_2 = new javax.swing.JLabel();
        get_pairing_accept_button = new javax.swing.JLabel();
        get_pairing_deny_button = new javax.swing.JLabel();
        get_pairing_panel = new javax.swing.JLabel();

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
        moving_window.setBounds(0, 0, 880, 110);

        get_pairing_title.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        get_pairing_title.setForeground(new java.awt.Color(255, 255, 255));
        get_pairing_title.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(get_pairing_title);
        get_pairing_title.setBounds(300, 40, 360, 37);
        
        get_pairing_status_true.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-status-false.png"))); // NOI18N
        getContentPane().add(get_pairing_status_true);
        get_pairing_status_true.setBounds(150, 230, 38, 38);

        get_pairing_status_false.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-status-false.png"))); // NOI18N
        getContentPane().add(get_pairing_status_false);
        get_pairing_status_false.setBounds(510, 230, 38, 38);

        get_pairing_nickname_1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        get_pairing_nickname_1.setText(SessionData.nickname);
        get_pairing_nickname_1.setToolTipText("");
        get_pairing_nickname_1.setAlignmentX(0.5F);
        get_pairing_nickname_1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        get_pairing_nickname_1.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(get_pairing_nickname_1);
        get_pairing_nickname_1.setBounds(210, 230, 240, 40);

        get_pairing_nickname_2.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        get_pairing_nickname_2.setText(nickname);
        get_pairing_nickname_2.setAlignmentX(0.5F);
        get_pairing_nickname_2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        get_pairing_nickname_2.setVerifyInputWhenFocusTarget(false);
        getContentPane().add(get_pairing_nickname_2);
        get_pairing_nickname_2.setBounds(570, 230, 240, 40);

        get_pairing_accept_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-accept-button.png"))); // NOI18N
        get_pairing_accept_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                get_pairing_accept_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(get_pairing_accept_button);
        get_pairing_accept_button.setBounds(150, 370, 237, 82);

        get_pairing_deny_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-deny-button.png"))); // NOI18N
        get_pairing_deny_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                get_pairing_deny_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(get_pairing_deny_button);
        get_pairing_deny_button.setBounds(510, 370, 237, 82);

        get_pairing_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-panel.png"))); // NOI18N
        getContentPane().add(get_pairing_panel);
        get_pairing_panel.setBounds(0, 0, 885, 586);

        pack();
    }                     

    private void get_pairing_accept_buttonMouseClicked(java.awt.event.MouseEvent evt) {      
        String dataSend = dataSocket.exportDataGetPairing(SessionData.nickname, true);
        socket.sendData(dataSend);
        get_pairing_status_true.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-status-true.png"))); // NOI18N
    }                                                      

    private void get_pairing_deny_buttonMouseClicked(java.awt.event.MouseEvent evt) { 
        String dataSend = dataSocket.exportDataGetPairing(SessionData.nickname, false);     
        socket.sendData(dataSend);
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

    public static void main(String args[]) {}

    // Variables declaration - do not modify                     
    private javax.swing.JLabel get_pairing_accept_button;
    private javax.swing.JLabel get_pairing_deny_button;
    private javax.swing.JLabel get_pairing_nickname_1;
    private javax.swing.JLabel get_pairing_nickname_2;
    private javax.swing.JLabel get_pairing_panel;
    private javax.swing.JLabel get_pairing_status_false;
    private javax.swing.JLabel get_pairing_status_true;
    private javax.swing.JLabel get_pairing_title;
    private javax.swing.JLabel moving_window;
    // End of variables declaration                   
}
