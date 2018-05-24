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
public class deletePane extends javax.swing.JFrame {

    /**
     * Creates new form newAccount
     */
    public deletePane() {
        initComponents();
        setLocationRelativeTo(null);
        jButton_no.setBackground(Color.decode("#d25722"));
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
        jPanel_header = new javax.swing.JPanel();
        jLabel_exit = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jLabel_text1 = new javax.swing.JLabel();
        jLabel_className = new javax.swing.JLabel();
        jButton_no = new javax.swing.JButton();
        jLabel_nameCode = new javax.swing.JLabel();
        jButton_yes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(52, 50, 46));
        jPanel1.setPreferredSize(new java.awt.Dimension(477, 733));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jPanel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 60));

        jLabel_title.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel_title.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_title.setText("Delete?");
        jPanel1.add(jLabel_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 340, 40));

        jLabel_text1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel_text1.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_text1.setText("Are you sure you want to delete the");
        jPanel1.add(jLabel_text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 290, 20));

        jLabel_className.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel_className.setForeground(new java.awt.Color(242, 242, 242));
        jLabel_className.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel_className, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 100, 20));

        jButton_no.setBackground(new java.awt.Color(179, 0, 0));
        jButton_no.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton_no.setForeground(new java.awt.Color(255, 255, 255));
        jButton_no.setText("NO");
        jButton_no.setBorder(null);
        jButton_no.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton_no, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, 120, 50));

        jLabel_nameCode.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel_nameCode.setForeground(new java.awt.Color(242, 242, 242));
        jPanel1.add(jLabel_nameCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 170, 20));

        jButton_yes.setBackground(new java.awt.Color(36, 143, 36));
        jButton_yes.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton_yes.setForeground(new java.awt.Color(255, 255, 255));
        jButton_yes.setText("YES");
        jButton_yes.setBorder(null);
        jButton_yes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jButton_yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 120, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_exitMouseClicked
        dispose();
    }//GEN-LAST:event_jLabel_exitMouseClicked

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
    public javax.swing.JButton jButton_no;
    public javax.swing.JButton jButton_yes;
    public javax.swing.JLabel jLabel_className;
    private javax.swing.JLabel jLabel_exit;
    public javax.swing.JLabel jLabel_nameCode;
    private javax.swing.JLabel jLabel_text1;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_header;
    // End of variables declaration//GEN-END:variables
}