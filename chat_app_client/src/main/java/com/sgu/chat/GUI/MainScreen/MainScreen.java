package com.sgu.chat.GUI.MainScreen;

import com.sgu.chat.GUI.Login.ChatLogin;
import com.sgu.chat.GUI.Pairing.WaitingPairing;
import com.sgu.chat.InternalData.SessionData;
import com.sgu.chat.socket_connection.DataSocket;
import com.sgu.chat.socket_connection.SocketConnection;
import com.sgu.chat.socket_connection.SocketHandler;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.swing.text.DefaultCaret;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class MainScreen extends javax.swing.JFrame {

    int x_Mouse, y_Mouse; // For Moving Window
    SocketConnection socket = new SocketConnection();
    DataSocket dataSocket = new DataSocket();

    public MainScreen(String opponentNickname) {
        initComponents();
        setSize(1090, 734);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        main_panel.setBackground(new Color(253, 245, 241));
        DefaultCaret caret = (DefaultCaret)main_area_chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        main_nickname_1.setText(SessionData.nickname);       
        main_nickname_1.setBounds(50 + (8 - SessionData.nickname.length())*(5), 140, 100, 50);
        main_nickname_2.setText(opponentNickname);
        main_nickname_2.setBounds(50 + (8 - opponentNickname.length())*(5), 550, 100, 50);
        main_title_nickname.setText(opponentNickname);
        
        
        
        socket.addListenConnection("send_message", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                String nickname = data.getString("nickname");
                String message = data.getString("message");
                System.out.println(nickname + ' ' + message);
//                main_area_chat.append("\n\n[" + nickname + "]: " + message);
                
                
                try
                {
                    if (SessionData.nickname.equals(nickname)){
                        doc.insertString(doc.getLength(), "\n\n[" + nickname + "]: " + message, right );
                        doc.setParagraphAttributes(doc.getLength(), 1, right, false);
                    }
                    else{
                        doc.insertString(doc.getLength(), "\n\n[" + nickname + "]: " + message, left );
                        doc.setParagraphAttributes(doc.getLength(), 1, left, false);
                    }
                }
                catch(Exception e) { System.out.println(e); }
            }
        });
        socket.addListenConnection("out_room_response", new SocketHandler() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                ChatLogin chatLogin = new ChatLogin();
                chatLogin.setVisible(true);
                dispose(); 
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_panel_scrollbar = new javax.swing.JScrollPane();
        main_chat_panel = new javax.swing.JTextPane();
        main_message_content = new javax.swing.JScrollPane();
        main_area_chat = new javax.swing.JTextArea();
        main_send_message_content = new javax.swing.JTextField();
        moving_window = new javax.swing.JLabel();
        main_title = new javax.swing.JLabel();
        main_title_nickname = new javax.swing.JLabel();
        main_nickname_1 = new javax.swing.JLabel();
        main_nickname_2 = new javax.swing.JLabel();
        main_send_message_button = new javax.swing.JLabel();
        main_exit_button = new javax.swing.JLabel();
        main_panel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        main_panel_scrollbar.setBackground(new java.awt.Color(255, 204, 204));
        main_panel_scrollbar.setBorder(null);
        main_panel_scrollbar.setOpaque(false);

        main_chat_panel.setEditable(false);
        main_chat_panel.setBackground(new java.awt.Color(253, 245, 241));
        main_chat_panel.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        main_chat_panel.setForeground(new java.awt.Color(102, 102, 102));
        main_panel_scrollbar.setViewportView(main_chat_panel);
        doc = main_chat_panel.getStyledDocument();

        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(left, new java.awt.Color(104, 104, 104));

        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(right, new java.awt.Color(248, 86, 86));

        getContentPane().add(main_panel_scrollbar);
        main_panel_scrollbar.setBounds(250, 90, 750, 480);

        main_message_content.setBackground(new java.awt.Color(255, 204, 204));
        main_message_content.setBorder(null);
        main_message_content.setOpaque(false);

        main_area_chat.setEditable(false);
        main_area_chat.setBackground(new java.awt.Color(253, 245, 241));
        main_area_chat.setColumns(20);
        main_area_chat.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        main_area_chat.setForeground(new java.awt.Color(102, 102, 102));
        main_area_chat.setRows(5);
        main_area_chat.setToolTipText("");
        main_area_chat.setWrapStyleWord(true);
        main_area_chat.setBorder(null);
        main_message_content.setViewportView(main_area_chat);

        getContentPane().add(main_message_content);
        main_message_content.setBounds(250, 90, 750, 480);

        main_send_message_content.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        main_send_message_content.setForeground(new java.awt.Color(102, 102, 102));
        main_send_message_content.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        main_send_message_content.setBorder(null);
        main_send_message_content.setOpaque(false);
        main_send_message_content.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                main_send_message_contentActionPerformed(evt);
            }
        });
        getContentPane().add(main_send_message_content);
        main_send_message_content.setBounds(290, 615, 610, 50);

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
        moving_window.setBounds(0, 0, 1090, 50);

        main_title.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        main_title.setForeground(new java.awt.Color(255, 255, 255));
        main_title.setText("Chating with");
        main_title.setToolTipText("");
        main_title.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(main_title);
        main_title.setBounds(200, 10, 140, 29);

        main_title_nickname.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        main_title_nickname.setForeground(new java.awt.Color(255, 255, 255));
        main_title_nickname.setText("Merevoli");
        main_title_nickname.setToolTipText("");
        getContentPane().add(main_title_nickname);
        main_title_nickname.setBounds(340, 10, 140, 29);

        main_nickname_1.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        main_nickname_1.setForeground(new java.awt.Color(255, 255, 255));
        main_nickname_1.setText("Merevoli");
        getContentPane().add(main_nickname_1);
        main_nickname_1.setBounds(50, 140, 100, 30);

        main_nickname_2.setFont(new java.awt.Font("Candara", 1, 24)); // NOI18N
        main_nickname_2.setForeground(new java.awt.Color(255, 255, 255));
        main_nickname_2.setText("Sara");
        getContentPane().add(main_nickname_2);
        main_nickname_2.setBounds(50, 550, 100, 50);

        main_send_message_button.setToolTipText("");
        main_send_message_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main_send_message_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(main_send_message_button);
        main_send_message_button.setBounds(920, 610, 60, 60);

        main_exit_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/main/main-quit-button.png"))); // NOI18N
        main_exit_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                main_exit_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(main_exit_button);
        main_exit_button.setBounds(60, 600, 76, 76);

        main_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/main/main-panel.png"))); // NOI18N
        getContentPane().add(main_panel);
        main_panel.setBounds(0, 0, 1090, 734);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void main_send_message_contentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_main_send_message_contentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_main_send_message_contentActionPerformed

    private void moving_windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMousePressed
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }//GEN-LAST:event_moving_windowMousePressed

    private void moving_windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - x_Mouse, y - y_Mouse);
    }//GEN-LAST:event_moving_windowMouseDragged

    private void main_send_message_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_send_message_buttonMouseClicked
        String message = main_send_message_content.getText();

        message = message.strip();

        if (!message.equals("")) {
            String dataSend = dataSocket.exportDataSendMessage(SessionData.nickname, message);
            socket.sendData(dataSend);
            main_send_message_content.setText("");
        }
    }//GEN-LAST:event_main_send_message_buttonMouseClicked

    private void main_exit_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_main_exit_buttonMouseClicked
        String dataSend = dataSocket.exportDataOutRoom(SessionData.nickname);
        socket.sendData(dataSend);
    }//GEN-LAST:event_main_exit_buttonMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea main_area_chat;
    private javax.swing.JTextPane main_chat_panel;
    private javax.swing.JLabel main_exit_button;
    private javax.swing.JScrollPane main_message_content;
    private javax.swing.JLabel main_nickname_1;
    private javax.swing.JLabel main_nickname_2;
    private javax.swing.JLabel main_panel;
    private javax.swing.JScrollPane main_panel_scrollbar;
    private javax.swing.JLabel main_send_message_button;
    private javax.swing.JTextField main_send_message_content;
    private javax.swing.JLabel main_title;
    private javax.swing.JLabel main_title_nickname;
    private javax.swing.JLabel moving_window;
    // End of variables declaration//GEN-END:variables
    StyledDocument doc;
    SimpleAttributeSet left;
    SimpleAttributeSet right;
}
