package Shell.Forms;
import Shell.UIController;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Toolkit;

public class UserInterface extends javax.swing.JFrame {

    public UserInterface() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Scroll = new javax.swing.JScrollPane();
        ContentPanel = new javax.swing.JPanel();
        MoviesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CinemasPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SessionsPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        FilterPanel = new javax.swing.JPanel();
        con_status = new javax.swing.JLabel();
        iconLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        exportMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        accountMenu = new javax.swing.JMenu();
        loginMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        AdminMenu = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CINEM4");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        Scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        ContentPanel.setPreferredSize(new java.awt.Dimension(1046, 500));

        javax.swing.GroupLayout ContentPanelLayout = new javax.swing.GroupLayout(ContentPanel);
        ContentPanel.setLayout(ContentPanelLayout);
        ContentPanelLayout.setHorizontalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
        );
        ContentPanelLayout.setVerticalGroup(
            ContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        Scroll.setViewportView(ContentPanel);

        MoviesPanel.setBackground(new java.awt.Color(29, 46, 56));
        MoviesPanel.setPreferredSize(new java.awt.Dimension(176, 230));
        MoviesPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MoviesPanelMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Фильмы");

        javax.swing.GroupLayout MoviesPanelLayout = new javax.swing.GroupLayout(MoviesPanel);
        MoviesPanel.setLayout(MoviesPanelLayout);
        MoviesPanelLayout.setHorizontalGroup(
            MoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviesPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MoviesPanelLayout.setVerticalGroup(
            MoviesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviesPanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CinemasPanel.setBackground(new java.awt.Color(29, 46, 56));
        CinemasPanel.setPreferredSize(new java.awt.Dimension(224, 230));
        CinemasPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CinemasPanelMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Кинотеатры");

        javax.swing.GroupLayout CinemasPanelLayout = new javax.swing.GroupLayout(CinemasPanel);
        CinemasPanel.setLayout(CinemasPanelLayout);
        CinemasPanelLayout.setHorizontalGroup(
            CinemasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CinemasPanelLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(22, 22, 22))
        );
        CinemasPanelLayout.setVerticalGroup(
            CinemasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CinemasPanelLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(84, 84, 84))
        );

        SessionsPanel.setBackground(new java.awt.Color(29, 46, 56));
        SessionsPanel.setPreferredSize(new java.awt.Dimension(175, 230));
        SessionsPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SessionsPanelMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 0));
        jLabel3.setText("Сеансы");

        javax.swing.GroupLayout SessionsPanelLayout = new javax.swing.GroupLayout(SessionsPanel);
        SessionsPanel.setLayout(SessionsPanelLayout);
        SessionsPanelLayout.setHorizontalGroup(
            SessionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SessionsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(68, 68, 68))
        );
        SessionsPanelLayout.setVerticalGroup(
            SessionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SessionsPanelLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(75, 75, 75))
        );

        FilterPanel.setBackground(new java.awt.Color(29, 46, 56));

        javax.swing.GroupLayout FilterPanelLayout = new javax.swing.GroupLayout(FilterPanel);
        FilterPanel.setLayout(FilterPanelLayout);
        FilterPanelLayout.setHorizontalGroup(
            FilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        FilterPanelLayout.setVerticalGroup(
            FilterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );

        con_status.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        con_status.setForeground(new java.awt.Color(255, 0, 0));
        con_status.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        con_status.setText("Связь с  БД: отсутствует");

        exportMenu.setText("Экспорт");

        jMenuItem1.setText("Отобразить панель экспорта");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        exportMenu.add(jMenuItem1);

        jMenuBar1.add(exportMenu);

        accountMenu.setText("Аккаунт");

        loginMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        loginMenu.setText("Войти");
        loginMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginMenuActionPerformed(evt);
            }
        });
        accountMenu.add(loginMenu);

        exitMenu.setText("Выход");
        exitMenu.setEnabled(false);
        exitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        accountMenu.add(exitMenu);

        jMenuBar1.add(accountMenu);

        AdminMenu.setText("Администрирование");

        jMenuItem3.setText("Панель администратора");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        AdminMenu.add(jMenuItem3);

        jMenuBar1.add(AdminMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(MoviesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(CinemasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(SessionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(FilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(con_status)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(FilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MoviesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CinemasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SessionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(con_status, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseEntered
        JPanel hovered = (JPanel) evt.getComponent();
        if(hovered.isEnabled())
            hovered.setBackground(new Color(41, 58, 86));
    }//GEN-LAST:event_jPanelMouseEntered

    private void jPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMouseExited
        JPanel hovered = (JPanel) evt.getComponent();
        if(hovered.isEnabled())
            hovered.setBackground(new Color(29, 46, 56));
    }//GEN-LAST:event_jPanelMouseExited

    private void CinemasPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CinemasPanelMouseClicked
        if(CinemasPanel.isEnabled())
        UIController.CinemaPanelClick();
    }//GEN-LAST:event_CinemasPanelMouseClicked

    private void MoviesPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoviesPanelMousePressed
        if(MoviesPanel.isEnabled())
        UIController.MoviePanelClick();
    }//GEN-LAST:event_MoviesPanelMousePressed

    private void SessionsPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SessionsPanelMousePressed
        if(SessionsPanel.isEnabled())
        UIController.SessionPanelClick();
    }//GEN-LAST:event_SessionsPanelMousePressed

    private void loginMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginMenuActionPerformed
        UIController.LoginForm();
    }//GEN-LAST:event_loginMenuActionPerformed

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuActionPerformed
        UIController.ExitAccount();
    }//GEN-LAST:event_exitMenuActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        UIController.Administration();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        UIController.ShowExportPanel();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenu AdminMenu;
    public javax.swing.JPanel CinemasPanel;
    public javax.swing.JPanel ContentPanel;
    public javax.swing.JPanel FilterPanel;
    public javax.swing.JPanel MoviesPanel;
    public javax.swing.JScrollPane Scroll;
    public javax.swing.JPanel SessionsPanel;
    private javax.swing.JMenu accountMenu;
    public javax.swing.JLabel con_status;
    public javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu exportMenu;
    public javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    public javax.swing.JMenuItem loginMenu;
    // End of variables declaration//GEN-END:variables
}
