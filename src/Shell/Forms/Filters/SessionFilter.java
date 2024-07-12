package Shell.Forms.Filters;

import Query.SessionQuery;
import Query.SearchQuery;
import Shell.UIController;

import com.github.lgooddatepicker.*;

public class SessionFilter extends javax.swing.JPanel 
{
    public SessionFilter() 
    {
        initComponents();
    }

    private SearchQuery GenerateSessionQuery()
    {
        SessionQuery query = new SessionQuery();
        
        query.SQLQueryText = "SELECT * FROM SessionTable";
        boolean hasWhere = false;
        
        if(movieNameCheckbox.isSelected())
        {
            query.SQLQueryText += " WHERE ";
            query.SQLQueryText += "MovieName LIKE \'%" + movieNameTextbox.getText() +"%\'";
            hasWhere = true;
        }
        
        if(cinemaNameCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "CinemaName LIKE \'%" + cinemaNameTextbox.getText() +"%\'";
        }
        
        if(priceCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "(MinPrice BETWEEN " + pricefrom.getValue() +" AND " + priceto.getValue();
            query.SQLQueryText += " OR MaxPrice BETWEEN " + pricefrom.getValue() + " AND " + priceto.getValue()+")";
        }
        
        if(dateCheckbox.isSelected())
        {
            if(datefrom.getDate() != null || dateto.getDate() != null)
            {
                if(!hasWhere)
                {
                    query.SQLQueryText += " WHERE ";
                    hasWhere = true;
                }
                else
                    query.SQLQueryText += " AND ";
                
                if(datefrom.getDate() == null)
                    query.SQLQueryText += "SessionDate <= \'" + dateto.getDate().toString() + "\'";
                else if(dateto.getDate() == null)
                    query.SQLQueryText += "SessionDate >= \'" + datefrom.getDate().toString() + "\'";
                else
                    query.SQLQueryText += "SessionDate >= \'" + datefrom.getDate().toString() + "\' AND SessionDate <= \'" + dateto.getDate() + "\'";
                
            }
           
        }
        
        if(timeCheckbox.isSelected())
        {
            if(timefrom.getTime() != null || timeto.getTime() != null)
            {
                if(!hasWhere)
                {
                    query.SQLQueryText += " WHERE ";
                    hasWhere = true;
                }
                else
                    query.SQLQueryText += " AND ";

                
                if(timefrom.getTime() == null)
                    query.SQLQueryText += "SessionStart <= \'" + timeto.getTime().toString() + "\'";
                else if(timeto.getTime() == null)
                    query.SQLQueryText += "SessionStart >= \'" + timefrom.getTime().toString() + "\'";
                else
                    query.SQLQueryText += "SessionStart >= \'" + timefrom.getTime().toString() + "\' AND SessionStart <= \'" + timeto.getTime() + "\'";
            }

        }

        return query;
    }
    
    public void AcceptCinemaLink(String cinemaName)
    {
        cinemaNameCheckbox.setSelected(true);
        cinemaNameTextbox.setText(cinemaName);
        UIController.AcceptFilter(GenerateSessionQuery());
    }
    
    public void AcceptMovieLink(String movieName)
    {
        movieNameCheckbox.setSelected(true);
        movieNameTextbox.setText(movieName);
        UIController.AcceptFilter(GenerateSessionQuery());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        movieNameCheckbox = new javax.swing.JCheckBox();
        cinemaNameCheckbox = new javax.swing.JCheckBox();
        dateCheckbox = new javax.swing.JCheckBox();
        timeCheckbox = new javax.swing.JCheckBox();
        priceCheckbox = new javax.swing.JCheckBox();
        movieNameTextbox = new javax.swing.JTextField();
        cinemaNameTextbox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pricefrom = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        priceto = new javax.swing.JSpinner();
        reset = new javax.swing.JButton();
        accept = new javax.swing.JButton();
        datefrom = new com.github.lgooddatepicker.components.DatePicker();
        jLabel6 = new javax.swing.JLabel();
        dateto = new com.github.lgooddatepicker.components.DatePicker();
        jLabel7 = new javax.swing.JLabel();
        timefrom = new com.github.lgooddatepicker.components.TimePicker();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        timeto = new com.github.lgooddatepicker.components.TimePicker();

        setBackground(new java.awt.Color(29, 46, 56));
        setPreferredSize(new java.awt.Dimension(1024, 182));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Фильтры киносеансов");

        movieNameCheckbox.setText("Фильм");

        cinemaNameCheckbox.setText("Кинотеатр");

        dateCheckbox.setText("Дата");

        timeCheckbox.setText("Время");

        priceCheckbox.setText("Цена");

        jLabel4.setText("от");

        pricefrom.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100000, 1));

        jLabel5.setText("до");

        priceto.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100000, 1));

        reset.setText("Сброс");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        accept.setText("Применить");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        datefrom.setBackground(new java.awt.Color(28, 39, 57));
        datefrom.setForeground(new java.awt.Color(28, 39, 57));

        jLabel6.setText("от");

        dateto.setBackground(new java.awt.Color(28, 39, 57));
        dateto.setForeground(new java.awt.Color(28, 39, 57));

        jLabel7.setText("до");

        jLabel8.setText("от");

        jLabel9.setText("до");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(priceCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pricefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                                .addComponent(reset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(accept))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(movieNameCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cinemaNameCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(movieNameTextbox, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                    .addComponent(cinemaNameTextbox))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dateCheckbox)
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabel6))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(timeCheckbox)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dateto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(timefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(timeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieNameCheckbox)
                    .addComponent(movieNameTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateCheckbox)
                    .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(dateto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cinemaNameCheckbox)
                    .addComponent(cinemaNameTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeCheckbox)
                    .addComponent(timefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(timeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(accept)
                        .addComponent(reset))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pricefrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(priceto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(priceCheckbox)))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        UIController.AcceptFilter(new SessionQuery("SELECT * FROM SessionTable"));
        UIController.ChangeFilter(new SessionFilter());
    }//GEN-LAST:event_resetActionPerformed

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        UIController.AcceptFilter(GenerateSessionQuery());
    }//GEN-LAST:event_acceptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JCheckBox cinemaNameCheckbox;
    private javax.swing.JTextField cinemaNameTextbox;
    public javax.swing.JCheckBox dateCheckbox;
    private com.github.lgooddatepicker.components.DatePicker datefrom;
    private com.github.lgooddatepicker.components.DatePicker dateto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JCheckBox movieNameCheckbox;
    private javax.swing.JTextField movieNameTextbox;
    private javax.swing.JCheckBox priceCheckbox;
    private javax.swing.JSpinner pricefrom;
    private javax.swing.JSpinner priceto;
    private javax.swing.JButton reset;
    private javax.swing.JCheckBox timeCheckbox;
    private com.github.lgooddatepicker.components.TimePicker timefrom;
    private com.github.lgooddatepicker.components.TimePicker timeto;
    // End of variables declaration//GEN-END:variables
}
