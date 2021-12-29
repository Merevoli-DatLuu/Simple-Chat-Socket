package com.sgu.chat.GUI.Login;

import com.sgu.chat.GUI.Function.FunctionWindow;
import com.sgu.chat.GUI.Pairing.WaitingPairing;
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

public class ChatLogin extends javax.swing.JFrame {
    int x_Mouse, y_Mouse; // For Moving Window
    SocketConnection socket = new SocketConnection();
    DataSocket dataSocket = new DataSocket();
    String nickname = "";
    boolean is_exit = false;
    
    public ChatLogin() {
        initComponents();
        setSize(930, 630);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        login_panel.setBackground(new Color(0, 0, 0, 0));
        
        if (SessionData.nickname != null){
            login_nickname_textfield.setText(SessionData.nickname);
        }
        
        socket.addListenConnection("send_nickname_response", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                boolean isSuccess = data.getBoolean("is_success");
                
                if (isSuccess){
                    SessionData.nickname = nickname;
                    
                    WaitingPairing waitingPairing = new WaitingPairing();
                    waitingPairing.setVisible(true);

                    dispose(); 
                }
                else{
                    String message = data.getString("message");
                    login_message.setText(message);
                }
            }
        });
        
        socket.addListenConnection("exit_app_response", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                dispose();
                System.exit(0);
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login_moving_window = new javax.swing.JLabel();
        login_message = new javax.swing.JLabel();
        login_nickname_textfield = new javax.swing.JTextField();
        login_nickname_label = new javax.swing.JLabel();
        login_nickname_input = new javax.swing.JLabel();
        login_start_button = new javax.swing.JLabel();
        login_panel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        login_moving_window.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                login_moving_windowMouseDragged(evt);
            }
        });
        login_moving_window.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                login_moving_windowMousePressed(evt);
            }
        });
        getContentPane().add(login_moving_window);
        login_moving_window.setBounds(0, 0, 920, 160);

        login_message.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        login_message.setForeground(new java.awt.Color(255, 102, 102));
        getContentPane().add(login_message);
        login_message.setBounds(200, 510, 460, 40);

        login_nickname_textfield.setBackground(new java.awt.Color(217, 217, 217));
        login_nickname_textfield.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        login_nickname_textfield.setText("Merevoli");
        login_nickname_textfield.setAlignmentX(0.6F);
        login_nickname_textfield.setBorder(null);
        login_nickname_textfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_nickname_textfieldMouseClicked(evt);
            }
        });
        login_nickname_textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_nickname_textfieldKeyPressed(evt);
            }
        });
        getContentPane().add(login_nickname_textfield);
        login_nickname_textfield.setBounds(230, 317, 380, 50);

        login_nickname_label.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        login_nickname_label.setText("Nickname");
        login_nickname_label.setAlignmentX(0.5F);
        getContentPane().add(login_nickname_label);
        login_nickname_label.setBounds(380, 260, 110, 40);

        login_nickname_input.setFont(new java.awt.Font("Candara", 0, 24)); // NOI18N
        login_nickname_input.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/login/login-nickname-input.png"))); // NOI18N
        getContentPane().add(login_nickname_input);
        login_nickname_input.setBounds(200, 300, 456, 75);

        login_start_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/login/login-start-button.png"))); // NOI18N
        login_start_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_start_buttonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_start_buttonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_start_buttonMouseExited(evt);
            }
        });
        getContentPane().add(login_start_button);
        login_start_button.setBounds(200, 420, 456, 76);

        login_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/login/login-panel.png"))); // NOI18N
        login_panel.setOpaque(true);
        getContentPane().add(login_panel);
        login_panel.setBounds(0, 0, 930, 630);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_moving_windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_moving_windowMousePressed
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }//GEN-LAST:event_login_moving_windowMousePressed

    private void login_moving_windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_moving_windowMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - x_Mouse, y - y_Mouse);
    }//GEN-LAST:event_login_moving_windowMouseDragged

    private void login_nickname_textfieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_nickname_textfieldMouseClicked
        login_nickname_textfield.requestFocus();
    }//GEN-LAST:event_login_nickname_textfieldMouseClicked

    private void login_start_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_start_buttonMouseEntered
        login_start_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/login/login-start-button-hover.png"))); // NOI18N
        login_start_button.setBounds(180, 405, 513, 114);
    }//GEN-LAST:event_login_start_buttonMouseEntered

    private void login_start_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_start_buttonMouseExited
        login_start_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/login/login-start-button.png"))); // NOI18N
        login_start_button.setBounds(200, 420, 456, 76);
    }//GEN-LAST:event_login_start_buttonMouseExited

    private void login_start_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_start_buttonMouseClicked
        String nickname = login_nickname_textfield.getText();
        System.out.println(nickname);
        nickname = nickname.strip();
         
        this.nickname = nickname;
        
        System.out.println(nickname);
        
        if (nickname.equals("")){
            login_message.setText("Nickname cannot be empty");
        }
        else{
            String data = dataSocket.exportDataSendNickname(nickname);
            socket.sendData(data);
        }
    }//GEN-LAST:event_login_start_buttonMouseClicked

    private void login_nickname_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_nickname_textfieldKeyPressed
        System.out.println(evt.getKeyCode());
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            if (!is_exit){
                is_exit = true;
                
                FunctionWindow functionWindow = new FunctionWindow(
                    "Thoát chương trình?", 
                    "Bạn có muốn thoát chương trình?"
                );
                
                functionWindow.setEvent(
                    new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            String nickname = "";
                            
                            if (SessionData.nickname != null){
                                nickname = SessionData.nickname;
                            }
                            
                            String dataSend = dataSocket.exportDataExitApp(nickname);
                            socket.sendData(dataSend);
                            functionWindow.dispose();
                        }
                    },
                    new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            functionWindow.dispose();
                            is_exit = false;
                        }
                    }
                );
                functionWindow.setVisible(true);
                
            }
        }
    }//GEN-LAST:event_login_nickname_textfieldKeyPressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel login_message;
    private javax.swing.JLabel login_moving_window;
    private javax.swing.JLabel login_nickname_input;
    private javax.swing.JLabel login_nickname_label;
    private javax.swing.JTextField login_nickname_textfield;
    private javax.swing.JLabel login_panel;
    private javax.swing.JLabel login_start_button;
    // End of variables declaration//GEN-END:variables
}
