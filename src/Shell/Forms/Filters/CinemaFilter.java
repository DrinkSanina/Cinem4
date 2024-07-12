package Shell.Forms.Filters;
import Shell.UIController;
import Query.SearchQuery;
import Query.CinemaQuery;

public class CinemaFilter extends javax.swing.JPanel
{
    
    public CinemaFilter()  
    {
        initComponents();
    }
        
    private SearchQuery GenerateCinemaQuery()
    {
        CinemaQuery query = new CinemaQuery();
        query.SQLQueryText = "Select * From CinemaTable";
        boolean hasWhere = false;
        
        if(nameCheckBox.isSelected())
        {
            query.SQLQueryText += " WHERE ";
            query.SQLQueryText += "CinemaName LIKE \'%" + nameTextBox.getText() +"%\'";
            hasWhere = true;
        }
        
        if(addressCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "Address LIKE \'%" + addressTextbox.getText() +"%\'";
        }
        
        if(amountCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "AmountOfHalls BETWEEN " + fromspin.getValue() +" AND " + tospin.getValue();
        }
        return query;
    }
    
    //При ссылочном переходе выставляет фильтр в соответствии с ссылкой
    public void AcceptSessionLink(String cinemaName)
    {
        nameCheckBox.setSelected(true);
        nameTextBox.setText(cinemaName);
        UIController.AcceptFilter(GenerateCinemaQuery());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameCheckBox = new javax.swing.JCheckBox();
        nameTextBox = new javax.swing.JTextField();
        addressCheckbox = new javax.swing.JCheckBox();
        addressTextbox = new javax.swing.JTextField();
        amountCheckbox = new javax.swing.JCheckBox();
        fromspin = new javax.swing.JSpinner();
        tospin = new javax.swing.JSpinner();
        accept = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(29, 46, 56));
        setPreferredSize(new java.awt.Dimension(1040, 182));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Фильтры кинотеатров");

        nameCheckBox.setText("Имя");

        addressCheckbox.setLabel("Адрес");

        amountCheckbox.setText("Число залов");

        fromspin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));

        tospin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 1000, 1));

        accept.setText("Применить");
        accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptActionPerformed(evt);
            }
        });

        reset.setText("Сброс");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel4.setText("от");

        jLabel5.setText("до");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(amountCheckbox)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fromspin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tospin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
                                .addComponent(reset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(accept))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(addressCheckbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addressTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameCheckBox)
                    .addComponent(nameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressCheckbox)
                    .addComponent(addressTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountCheckbox)
                    .addComponent(fromspin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tospin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(accept)
                    .addComponent(reset))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        UIController.AcceptFilter(GenerateCinemaQuery());
    }//GEN-LAST:event_acceptActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
       UIController.AcceptFilter(new CinemaQuery());
       UIController.ChangeFilter(new CinemaFilter());
    }//GEN-LAST:event_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JCheckBox addressCheckbox;
    private javax.swing.JTextField addressTextbox;
    private javax.swing.JCheckBox amountCheckbox;
    private javax.swing.JSpinner fromspin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JCheckBox nameCheckBox;
    private javax.swing.JTextField nameTextBox;
    private javax.swing.JButton reset;
    private javax.swing.JSpinner tospin;
    // End of variables declaration//GEN-END:variables
}
