package Shell.Forms.Records;

import Shell.UIController;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JLabel;

public class SessionRecord extends javax.swing.JPanel 
{


    public SessionRecord() 
    {
        initComponents();
        
        Font font = MovieName.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        
        MovieName.setFont(font.deriveFont(attributes));
        CinemaName.setFont(font.deriveFont(attributes));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        id = new javax.swing.JLabel();
        MovieName = new javax.swing.JLabel();
        CinemaName = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        MinP = new javax.swing.JLabel();
        MaxP = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        booking = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(29, 46, 56)));
        setForeground(new java.awt.Color(255, 102, 0));

        id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        id.setText("id");

        MovieName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        MovieName.setForeground(new java.awt.Color(255, 102, 0));
        MovieName.setText("Name");
        MovieName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MovieName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MovieNameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MovieNameMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MovieNameMousePressed(evt);
            }
        });

        CinemaName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CinemaName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CinemaName.setText("Cinema");
        CinemaName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CinemaName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MovieNameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CinemaNameMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CinemaNameMousePressed(evt);
            }
        });

        Date.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Date.setText("Date");

        Time.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("Time");

        MinP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MinP.setText("Min");

        MaxP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MaxP.setText("Max");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Можно забронировать ");

        booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Цена от");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("до");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(id)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MovieName, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CinemaName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Date)
                    .addComponent(Time))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MinP)
                    .addComponent(MaxP))
                .addGap(72, 72, 72)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(booking)
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(id)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(booking)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Time)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MinP)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(MaxP))
                                .addGap(20, 20, 20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MovieName)
                                    .addComponent(CinemaName))
                                .addGap(28, 28, 28))))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingActionPerformed
        booking.setSelected(!booking.isSelected());
    }//GEN-LAST:event_bookingActionPerformed

    private void MovieNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MovieNameMouseEntered
        JLabel hovered = (JLabel) evt.getComponent();
        hovered.setForeground(Color.red);
    }//GEN-LAST:event_MovieNameMouseEntered

    private void MovieNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MovieNameMouseExited
        JLabel hovered = (JLabel) evt.getComponent();
        hovered.setForeground(new Color(255,102,0));
    }//GEN-LAST:event_MovieNameMouseExited

    private void CinemaNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CinemaNameMouseExited
        JLabel hovered = (JLabel) evt.getComponent();
        hovered.setForeground(new Color(193,193,193));
    }//GEN-LAST:event_CinemaNameMouseExited

    private void MovieNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MovieNameMousePressed
        UIController.CheckMovie(MovieName.getText());
    }//GEN-LAST:event_MovieNameMousePressed

    private void CinemaNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CinemaNameMousePressed
        UIController.CheckCinema(CinemaName.getText());
    }//GEN-LAST:event_CinemaNameMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel CinemaName;
    public javax.swing.JLabel Date;
    public javax.swing.JLabel MaxP;
    public javax.swing.JLabel MinP;
    public javax.swing.JLabel MovieName;
    public javax.swing.JLabel Time;
    public javax.swing.JCheckBox booking;
    public javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
