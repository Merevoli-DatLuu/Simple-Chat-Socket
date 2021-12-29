package com.sgu.chat.GUI.Function;

import java.awt.Color;
import java.awt.event.MouseAdapter;

public class FunctionWindow extends javax.swing.JFrame {
    int x_Mouse, y_Mouse; // For Moving Window

    public FunctionWindow(String title, String content) {
        initComponents(title, content);
        setSize(553, 366);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        function_panel.setBackground(new Color(0, 0, 0, 0));
    }
    
    public void setEvent(MouseAdapter accept, MouseAdapter deny){
        function_accept_button.addMouseListener(accept);
        function_deny_button.addMouseListener(deny);
    }

    private void initComponents(String title, String content) {

        moving_window = new javax.swing.JLabel();
        function_title = new javax.swing.JLabel();
        function_content = new javax.swing.JLabel();
        function_accept_button = new javax.swing.JLabel();
        function_deny_button = new javax.swing.JLabel();
        function_panel = new javax.swing.JLabel();

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
        moving_window.setBounds(0, 0, 560, 70);

        function_title.setFont(new java.awt.Font("Cambria", 1, 24)); // NOI18N
        function_title.setForeground(new java.awt.Color(255, 255, 255));
        function_title.setText(title);
        getContentPane().add(function_title);
        function_title.setBounds(30, 20, 390, 29);

        function_content.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        function_content.setText(content);
        getContentPane().add(function_content);
        function_content.setBounds(90, 150, 390, 29);

        function_accept_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/function-window/function-window-accept-button.png"))); // NOI18N
        getContentPane().add(function_accept_button);
        function_accept_button.setBounds(90, 240, 142, 49);

        function_deny_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/function-window/function-window-deny-button.png"))); // NOI18N
        getContentPane().add(function_deny_button);
        function_deny_button.setBounds(330, 240, 141, 48);

        function_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/function-window/function-window.png"))); // NOI18N
        getContentPane().add(function_panel);
        function_panel.setBounds(0, 0, 553, 366);

        pack();
    }

    private void moving_windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMousePressed
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }

    private void moving_windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - x_Mouse, y - y_Mouse);
    }

    private void function_accept_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_function_accept_buttonMouseClicked
        // TODO add your handling code here:
    }

    private void function_deny_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_function_deny_buttonMouseClicked
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FunctionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FunctionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FunctionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FunctionWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {}
        });
    }

    private javax.swing.JLabel function_accept_button;
    private javax.swing.JLabel function_content;
    private javax.swing.JLabel function_deny_button;
    private javax.swing.JLabel function_panel;
    private javax.swing.JLabel function_title;
    private javax.swing.JLabel moving_window;
}
