package com.sgu.chat.GUI.Pairing;

import java.awt.Color;

public class WaitingPairing_2 extends javax.swing.JFrame {
    int x_Mouse, y_Mouse; // For Moving Window

    public WaitingPairing_2() {
        initComponents();
        setSize(664, 439);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        waiting_pairing_panel.setBackground(new Color(0, 0, 0, 0));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        waiting_pairing_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-exit-button.png"))); // NOI18N
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
        waiting_pairing_button.setBounds(180, 240, 268, 140);

        waiting_pairing_panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/waiting_pairing/pairing-panel.png"))); // NOI18N
        getContentPane().add(waiting_pairing_panel);
        waiting_pairing_panel.setBounds(0, 0, 664, 439);
        waiting_pairing_panel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moving_windowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMousePressed
        x_Mouse = evt.getX();
        y_Mouse = evt.getY();
    }//GEN-LAST:event_moving_windowMousePressed

    private void moving_windowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moving_windowMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - x_Mouse, y - y_Mouse);
    }//GEN-LAST:event_moving_windowMouseDragged

    private void waiting_pairing_buttonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waiting_pairing_buttonMouseEntered
        waiting_pairing_button.setIcon(new javax.swing.ImageIcon("/com/sgu/chat/images/waiting_pairing/pairing-exit-button.png")); // NOI18N
        waiting_pairing_button.setBounds(180, 240, 268, 140);
    }//GEN-LAST:event_waiting_pairing_buttonMouseEntered

    private void waiting_pairing_buttonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waiting_pairing_buttonMouseExited
        waiting_pairing_button.setIcon(new javax.swing.ImageIcon("/com/sgu/chat/images/waiting-pairing/pairing-exit-button-hover.png")); // NOI18N
        waiting_pairing_button.setBounds(180, 240, 268, 140);
    }//GEN-LAST:event_waiting_pairing_buttonMouseExited

    private void waiting_pairing_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_waiting_pairing_buttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_waiting_pairing_buttonMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WaitingPairing_2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WaitingPairing_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel moving_window;
    private javax.swing.JLabel waiting_pairing_button;
    private javax.swing.JLabel waiting_pairing_panel;
    // End of variables declaration//GEN-END:variables
}
