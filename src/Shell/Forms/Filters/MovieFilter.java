package Shell.Forms.Filters;
import Engine.Controller;
import Query.MovieQuery;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Query.SearchQuery;
import Shell.UIController;
import java.util.Calendar;



public class MovieFilter extends javax.swing.JPanel {


    DefaultListModel<String> model;
    
    public MovieFilter() 
    {
        initComponents();
        GenreInit();
        CountryInit();
        model = new DefaultListModel<String>();
        tospin.setValue(java.util.Calendar.getInstance().get(Calendar.YEAR));
        actorsListBox.setModel(model);
    }
    
    private void GenreInit()
    {
        genreCombobox.removeAllItems();
        String sql = "SELECT DISTINCT Genre FROM MoviesTable";
        ArrayList<String> values = Controller.SelectOptions(sql);
        
        for(int i = 0; i < values.size(); i++)
            genreCombobox.addItem(values.get(i));
    }
    
    private void CountryInit()
    {
        countryCombobox.removeAllItems();
        String sql = "SELECT DISTINCT Country FROM MoviesTable";
        ArrayList<String> values = Controller.SelectOptions(sql);
        
        for(int i = 0; i < values.size(); i++)
            countryCombobox.addItem(values.get(i));
    }
    
    private SearchQuery GenerateMovieQuery()
    {
        
        MovieQuery query = new MovieQuery();
        
        query.SQLQueryText = "SELECT * FROM MoviesTable";
        boolean hasWhere = false;
        
        if(movieNameCheckBox.isSelected())
        {
            query.SQLQueryText += " WHERE ";
            query.SQLQueryText += "MovieName LIKE \'%" + movieName.getText() +"%\'";
            hasWhere = true;
        }
        
        if(genreCheckBox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "Genre = \"" + genreCombobox.getSelectedItem() + "\"";
        }
        
        if(yearCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                 query.SQLQueryText += " WHERE ";
                 hasWhere = true;
            }
            else
                query.SQLQueryText +=" AND ";
            
            query.SQLQueryText += "Year BETWEEN " + fromspin.getValue() +" AND " + tospin.getValue();
        }
        
        if(countryCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                query.SQLQueryText += " WHERE ";
                hasWhere = true;
            }
            else
                query.SQLQueryText += " AND ";
            
            query.SQLQueryText += "Country = \"" + countryCombobox.getSelectedItem() + "\"";
        }
        
        if(directorCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                query.SQLQueryText += " WHERE ";
                hasWhere = true;
            }
            else
                query.SQLQueryText += " AND ";
            
            query.SQLQueryText += "Director LIKE \'%" + directorTextbox.getText() +"%\'";
        }
        
        if(actorsCheckbox.isSelected())
        {
            if(!hasWhere)
            {
                query.SQLQueryText += " WHERE ";
                hasWhere = true;
            }
            else
                query.SQLQueryText += " AND ";
            
            for(int i = 0; i < model.getSize(); i++)
            {
                query.SQLQueryText += "Actors LIKE \'%" + model.get(i) +"%\'";
                
                if(i < model.getSize() -1)
                    query.SQLQueryText += " AND ";
            }
            
        }
        
        return query;
    }
    
    public void AcceptSessionLink(String movieNameL)
    {
        movieNameCheckBox.setSelected(true);
        movieName.setText(movieNameL);
        UIController.AcceptFilter(GenerateMovieQuery());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        movieNameCheckBox = new javax.swing.JCheckBox();
        genreCheckBox = new javax.swing.JCheckBox();
        yearCheckbox = new javax.swing.JCheckBox();
        countryCheckbox = new javax.swing.JCheckBox();
        movieName = new javax.swing.JTextField();
        fromspin = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tospin = new javax.swing.JSpinner();
        genreCombobox = new javax.swing.JComboBox<>();
        countryCombobox = new javax.swing.JComboBox<>();
        directorCheckbox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        actorsListBox = new javax.swing.JList<>();
        actorsCheckbox = new javax.swing.JCheckBox();
        addActor = new javax.swing.JButton();
        deleteActor = new javax.swing.JButton();
        actorField = new javax.swing.JTextField();
        accept = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        directorTextbox = new javax.swing.JTextField();

        setBackground(new java.awt.Color(29, 46, 56));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Фильтры фильмов");

        movieNameCheckBox.setText("Название фильма");

        genreCheckBox.setText("Жанр");

        yearCheckbox.setText("Год выхода");

        countryCheckbox.setText("Страна производства");

        fromspin.setModel(new javax.swing.SpinnerNumberModel(1896, 1896, 3000, 1));

        jLabel4.setText("от");

        jLabel5.setText("до");

        tospin.setModel(new javax.swing.SpinnerNumberModel(1896, 1896, 3000, 1));

        genreCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        countryCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        directorCheckbox.setText("Режиссер");

        jScrollPane1.setViewportView(actorsListBox);

        actorsCheckbox.setText("Актеры");

        addActor.setText("+");
        addActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActorActionPerformed(evt);
            }
        });

        deleteActor.setText("-");
        deleteActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActorActionPerformed(evt);
            }
        });

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

        directorTextbox.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(countryCheckbox)
                        .addGap(18, 18, 18)
                        .addComponent(countryCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(movieNameCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(movieName, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yearCheckbox)
                            .addComponent(genreCheckBox))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fromspin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tospin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(genreCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(actorField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addActor, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteActor, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(reset)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(accept))))
                            .addComponent(actorsCheckbox))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(directorCheckbox)
                        .addGap(18, 18, 18)
                        .addComponent(directorTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(movieNameCheckBox)
                                    .addComponent(movieName)
                                    .addComponent(directorCheckbox)
                                    .addComponent(jLabel1)
                                    .addComponent(directorTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(genreCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(genreCombobox)
                                        .addComponent(actorsCheckbox)))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(yearCheckbox)
                                    .addComponent(fromspin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tospin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(countryCheckbox)
                                    .addComponent(countryCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(actorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(deleteActor)
                                    .addComponent(addActor)
                                    .addComponent(accept)
                                    .addComponent(reset)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActorActionPerformed
        if(actorField.getText() != "")
        {
            model.addElement(actorField.getText());
            actorField.setText("");
        }
    }//GEN-LAST:event_addActorActionPerformed

    private void deleteActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActorActionPerformed
        if(actorsListBox.getSelectedIndex() >= 0)
        {
            model.remove(actorsListBox.getSelectedIndex());
        }
    }//GEN-LAST:event_deleteActorActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
       UIController.AcceptFilter(new MovieQuery("SELECT * FROM MoviesTable"));
       UIController.ChangeFilter(new MovieFilter());
    }//GEN-LAST:event_resetActionPerformed

    private void acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptActionPerformed
        UIController.AcceptFilter(GenerateMovieQuery());
    }//GEN-LAST:event_acceptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accept;
    private javax.swing.JTextField actorField;
    private javax.swing.JCheckBox actorsCheckbox;
    private javax.swing.JList<String> actorsListBox;
    private javax.swing.JButton addActor;
    private javax.swing.JCheckBox countryCheckbox;
    private javax.swing.JComboBox<String> countryCombobox;
    private javax.swing.JButton deleteActor;
    private javax.swing.JCheckBox directorCheckbox;
    private javax.swing.JTextField directorTextbox;
    private javax.swing.JSpinner fromspin;
    private javax.swing.JCheckBox genreCheckBox;
    private javax.swing.JComboBox<String> genreCombobox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField movieName;
    private javax.swing.JCheckBox movieNameCheckBox;
    private javax.swing.JButton reset;
    private javax.swing.JSpinner tospin;
    private javax.swing.JCheckBox yearCheckbox;
    // End of variables declaration//GEN-END:variables
}
