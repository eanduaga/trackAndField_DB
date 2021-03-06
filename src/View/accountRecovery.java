/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;

/**
 *
 * @author Eider
 */
public class accountRecovery extends javax.swing.JFrame {

    /**
     * Creates new form newAccount
     */
    public accountRecovery() {
        initComponents();
        setLocationRelativeTo(null);
        jButton_send.setBackground(Color.decode("#d25722"));
        jLabel_title.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel_email = new javax.swing.JLabel();
        jTextField_email = new javax.swing.JTextField();
        jButton_send = new javax.swing.JButton();
        jPanel_header = new javax.swing.JPanel();
        jLabel_exit = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_back = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_text1 = new javax.swing.JLabel();
        jLabel_text2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 50, 46));
        jPanel1.setPreferredSize(new java.awt.Dimension(477, 733));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 340, 10));

        jLabel_email.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel_email.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_email.setText("EMAIL ACCOUNT:");
        jPanel1.add(jLabel_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        jTextField_email.setBackground(new java.awt.Color(52, 50, 46));
        jTextField_email.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jTextField_email.setForeground(new java.awt.Color(240, 240, 240));
        jTextField_email.setText("Enter your email account");
        jTextField_email.setBorder(null);
        jTextField_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_emailFocusLost(evt);
            }
        });
        jPanel1.add(jTextField_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 340, 30));

        jButton_send.setBackground(new java.awt.Color(210, 87, 34));
        jButton_send.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton_send.setForeground(new java.awt.Color(255, 255, 255));
        jButton_send.setText("SEND");
        jButton_send.setBorder(null);
        jButton_send.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 120, 50));

        jPanel_header.setBackground(new java.awt.Color(52, 50, 46));
        jPanel_header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_headerMouseDragged(evt);
            }
        });
        jPanel_header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_headerMousePressed(evt);
            }
        });
        jPanel_header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/30x30_exit4.png"))); // NOI18N
        jLabel_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_exitMouseClicked(evt);
            }
        });
        jPanel_header.add(jLabel_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 50, 60));

        jLabel_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/30x30_minimize_1.png"))); // NOI18N
        jLabel_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
        });
        jPanel_header.add(jLabel_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 50, 60));

        jLabel_back.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel_back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/30x30_backarrow3.png"))); // NOI18N
        jLabel_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_header.add(jLabel_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 50, 60));

        jPanel1.add(jPanel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 60));

        jLabel_title.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel_title.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_title.setText("Forgot your password?");
        jPanel1.add(jLabel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 340, 40));

        jLabel_text1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_text1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_text1.setText("Enter your email address to get your password.");
        jPanel1.add(jLabel_text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 340, 20));

        jLabel_text2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_text2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_text2.setText("You may need to check your spam folder.");
        jPanel1.add(jLabel_text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 340, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_emailFocusLost
        if(jTextField_email.getText().equals(""))
        {
            jTextField_email.setText("Enter your email account");
        }
    }//GEN-LAST:event_jTextField_emailFocusLost

    private void jLabel_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel_exitMouseClicked

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        setState(ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    private void jTextField_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_emailFocusGained
        if(jTextField_email.getText().equals("Enter your email account"))
        {
            jTextField_email.setText("");
            jTextField_email.setCaretColor(Color.decode("#f2f2f2"));
        }
    }//GEN-LAST:event_jTextField_emailFocusGained

    private void jPanel_headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel_headerMouseDragged

    private void jPanel_headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel_headerMousePressed

    int xMouse;
    int yMouse;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton_send;
    public javax.swing.JLabel jLabel_back;
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_exit;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JLabel jLabel_text1;
    private javax.swing.JLabel jLabel_text2;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_header;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JTextField jTextField_email;
    // End of variables declaration//GEN-END:variables
}
