/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Eider
 */

public class showOneAth extends javax.swing.JFrame {

    /**
     * Creates new form addCompetition
     */
    public showOneAth() {
        initComponents();
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
        jLabel_idAth = new javax.swing.JLabel();
        jLabel_addressAth = new javax.swing.JLabel();
        jLabel_homeTownAth = new javax.swing.JLabel();
        jLabel_nationalityAth = new javax.swing.JLabel();
        jPanel_header = new javax.swing.JPanel();
        jLabel_exit = new javax.swing.JLabel();
        jLabel_minimize = new javax.swing.JLabel();
        jLabel_viewTitle = new javax.swing.JLabel();
        jLabel_perBestAth = new javax.swing.JLabel();
        jLabel_favDisciplineAth = new javax.swing.JLabel();
        jLabel_phoneNumAth = new javax.swing.JLabel();
        jLabel_emailAth = new javax.swing.JLabel();
        jLabel_birthDateAth = new javax.swing.JLabel();
        jLabel_teamAth = new javax.swing.JLabel();
        jLabel_numMedalsAth = new javax.swing.JLabel();
        jLabel_seBestAth = new javax.swing.JLabel();
        jLabel_nameSurname = new javax.swing.JLabel();
        jLabel_countryAth = new javax.swing.JLabel();
        jLabel_idTitle = new javax.swing.JLabel();
        jLabel_countryTitle = new javax.swing.JLabel();
        jLabel_homeTownTitle = new javax.swing.JLabel();
        jLabel_addressTitle = new javax.swing.JLabel();
        jLabel_nationalityTitle = new javax.swing.JLabel();
        jLabel_birthDateTitle = new javax.swing.JLabel();
        jLabel_emailTitle = new javax.swing.JLabel();
        jLabel_phoneNumTitle = new javax.swing.JLabel();
        jLabel_favDisciplineTitle = new javax.swing.JLabel();
        jLabel_perBestTitle = new javax.swing.JLabel();
        jLabel_seBestTitle = new javax.swing.JLabel();
        jLabel_numMedalsTitle = new javax.swing.JLabel();
        jLabel_teamTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(52, 50, 46)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_idAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_idAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_idAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 200, 40));

        jLabel_addressAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_addressAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_addressAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 200, 40));

        jLabel_homeTownAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_homeTownAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_homeTownAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 200, 40));

        jLabel_nationalityAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_nationalityAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_nationalityAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 200, 40));

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
        jPanel_header.add(jLabel_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 50, 50));

        jLabel_minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Images/30x30_minimize_1.png"))); // NOI18N
        jLabel_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
        });
        jPanel_header.add(jLabel_minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 50, 50));

        jLabel_viewTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_viewTitle.setForeground(new java.awt.Color(210, 87, 34));
        jLabel_viewTitle.setText("ATHLETE PROFILE");
        jPanel_header.add(jLabel_viewTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 280, 30));

        jPanel1.add(jPanel_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 50));

        jLabel_perBestAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_perBestAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_perBestAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 200, 40));

        jLabel_favDisciplineAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_favDisciplineAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_favDisciplineAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 200, 40));

        jLabel_phoneNumAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_phoneNumAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_phoneNumAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 200, 40));

        jLabel_emailAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_emailAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_emailAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 200, 40));

        jLabel_birthDateAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_birthDateAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_birthDateAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 200, 40));

        jLabel_teamAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_teamAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_teamAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 650, 200, 40));

        jLabel_numMedalsAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_numMedalsAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_numMedalsAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 610, 200, 40));

        jLabel_seBestAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_seBestAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_seBestAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 200, 40));

        jLabel_nameSurname.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel_nameSurname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel_nameSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 420, 50));

        jLabel_countryAth.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel_countryAth.setForeground(new java.awt.Color(52, 50, 46));
        jPanel1.add(jLabel_countryAth, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 200, 40));

        jLabel_idTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_idTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_idTitle.setText("ID:");
        jPanel1.add(jLabel_idTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, 40));

        jLabel_countryTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_countryTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_countryTitle.setText("Country:");
        jPanel1.add(jLabel_countryTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 40));

        jLabel_homeTownTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_homeTownTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_homeTownTitle.setText("Home Town:");
        jPanel1.add(jLabel_homeTownTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, 40));

        jLabel_addressTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_addressTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_addressTitle.setText("Address:");
        jPanel1.add(jLabel_addressTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 289, -1, 40));

        jLabel_nationalityTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_nationalityTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_nationalityTitle.setText("Nationality:");
        jPanel1.add(jLabel_nationalityTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 329, -1, 40));

        jLabel_birthDateTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_birthDateTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_birthDateTitle.setText("Birth Date:");
        jPanel1.add(jLabel_birthDateTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 369, -1, 40));

        jLabel_emailTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_emailTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_emailTitle.setText("Email Address:");
        jPanel1.add(jLabel_emailTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 409, -1, 40));

        jLabel_phoneNumTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_phoneNumTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_phoneNumTitle.setText("Phone Number:");
        jPanel1.add(jLabel_phoneNumTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, 40));

        jLabel_favDisciplineTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_favDisciplineTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_favDisciplineTitle.setText("Favourite Discipline:");
        jPanel1.add(jLabel_favDisciplineTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 489, -1, 40));

        jLabel_perBestTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_perBestTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_perBestTitle.setText("Personal Best:");
        jPanel1.add(jLabel_perBestTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 529, -1, 40));

        jLabel_seBestTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_seBestTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_seBestTitle.setText("Season Best:");
        jPanel1.add(jLabel_seBestTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 570, -1, 40));

        jLabel_numMedalsTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_numMedalsTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_numMedalsTitle.setText("Number of Medals:");
        jPanel1.add(jLabel_numMedalsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 609, -1, 40));

        jLabel_teamTitle.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel_teamTitle.setForeground(new java.awt.Color(52, 50, 46));
        jLabel_teamTitle.setText("Team:");
        jPanel1.add(jLabel_teamTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 649, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
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

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        setState(ICONIFIED);
    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    int xMouse;
    int yMouse;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel jLabel_addressAth;
    private javax.swing.JLabel jLabel_addressTitle;
    public javax.swing.JLabel jLabel_birthDateAth;
    private javax.swing.JLabel jLabel_birthDateTitle;
    public javax.swing.JLabel jLabel_countryAth;
    private javax.swing.JLabel jLabel_countryTitle;
    public javax.swing.JLabel jLabel_emailAth;
    private javax.swing.JLabel jLabel_emailTitle;
    private javax.swing.JLabel jLabel_exit;
    public javax.swing.JLabel jLabel_favDisciplineAth;
    private javax.swing.JLabel jLabel_favDisciplineTitle;
    public javax.swing.JLabel jLabel_homeTownAth;
    private javax.swing.JLabel jLabel_homeTownTitle;
    public javax.swing.JLabel jLabel_idAth;
    private javax.swing.JLabel jLabel_idTitle;
    private javax.swing.JLabel jLabel_minimize;
    public javax.swing.JLabel jLabel_nameSurname;
    public javax.swing.JLabel jLabel_nationalityAth;
    private javax.swing.JLabel jLabel_nationalityTitle;
    public javax.swing.JLabel jLabel_numMedalsAth;
    private javax.swing.JLabel jLabel_numMedalsTitle;
    public javax.swing.JLabel jLabel_perBestAth;
    private javax.swing.JLabel jLabel_perBestTitle;
    public javax.swing.JLabel jLabel_phoneNumAth;
    private javax.swing.JLabel jLabel_phoneNumTitle;
    public javax.swing.JLabel jLabel_seBestAth;
    private javax.swing.JLabel jLabel_seBestTitle;
    public javax.swing.JLabel jLabel_teamAth;
    private javax.swing.JLabel jLabel_teamTitle;
    private javax.swing.JLabel jLabel_viewTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_header;
    // End of variables declaration//GEN-END:variables
}
