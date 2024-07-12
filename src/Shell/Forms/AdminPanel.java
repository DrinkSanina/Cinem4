package Shell.Forms;
import Query.*;
import Engine.Controller;
import Shell.UIController;
import Query.SearchQuery.TableType;
import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminPanel extends javax.swing.JFrame {

    private DefaultListModel<String> model;
    private DefaultListModel<String> actorsModel;
    private TableType dispType;
    private ArrayList<SearchQuery> displayableData;
    
    public AdminPanel(String adminName) 
    {
        initComponents();
        accountName.setText("Администратор: "+adminName);
        model = new DefaultListModel<String>();
        actorsModel = new DefaultListModel<String>();
        displayableData = new ArrayList<SearchQuery>();
        recordlist.setModel(model);
        actorsList.setModel(actorsModel);
        GetMovieData();
        
        tabs.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                switch(tabs.getSelectedIndex())
                {
                    case 0: GetMovieData();break;
                    case 1: GetCinemaData();break;
                    case 2: GetSessionData();break;
                }
            }
        });
        
        recordlist.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                int index = recordlist.getSelectedIndex();
                if(index >= 0)
                {
                    switch(dispType)
                    {
                        case MoviesTable: 
                        {                            
                            MovieQuery record = (MovieQuery) displayableData.get(index);
                            
                            fmovieName.setText(record.movieName);
                            fgenre.setText(record.genre);
                            fyear.setValue(record.year);
                            fcountry.setText(record.country);
                            fduration.setText(String.valueOf(record.duration));
                            fdirector.setText(record.director);
                            fdescription.setText(record.description);
                            frating.setText(String.valueOf(record.rating));
                            
                            
                            
                            actorsModel.clear();
                            for(int i = 0; i < record.actors.size();i++)
                                actorsModel.addElement(record.actors.get(i));
                            
                            break;
                        }
                        case CinemaTable:
                        {
                            CinemaQuery record = (CinemaQuery) displayableData.get(index);
                            
                            cinemaName.setText(record.cinemaName);
                            address.setText(record.address);
                            note.setText(record.note);
                            phone.setText(record.phoneNumber);
                            halls.setValue(record.amountOfHalls);
                            
                            break;
                        }
                        case SessionTable:
                        {
                            SessionQuery record = (SessionQuery) displayableData.get(index);
                            
                            moviecbox.setSelectedItem(record.movieName);
                            cinemacbox.setSelectedItem(record.cinemaName);
                            
                            date.setDate(LocalDate.parse(record.sessionDate));
                            time.setTime(LocalTime.parse(record.sessionStart));
                            
                            minprice.setValue(record.minPrice);
                            maxprice.setValue(record.maxPrice);
                            
                            booking.setSelected(record.booking);
                            
                            break;
                        }
                    }
                }
            }
            
        });
    }
    
    
    
    private void GetMovieData()
    {
        dispType = TableType.MoviesTable;
        model.removeAllElements();
        displayableData.clear();
        moviecbox.removeAllItems();
        displayableData = Controller.DirectSearch(new MovieQuery("SELECT * FROM MoviesTable"));
        
        for(int i = 0; i < displayableData.size();i++)
        {
            MovieQuery mq = (MovieQuery) displayableData.get(i);
            model.addElement(mq.movieName);
            moviecbox.addItem(mq.movieName);
        }
        
    }
    
    private void GetCinemaData()
    {
        dispType = TableType.CinemaTable;
        model.removeAllElements();
        cinemacbox.removeAllItems();
        displayableData.clear();
        displayableData = Controller.DirectSearch(new CinemaQuery("SELECT * FROM CinemaTable"));
        
        for(int i = 0; i < displayableData.size();i++)
        {
            CinemaQuery cq = (CinemaQuery) displayableData.get(i);
            model.addElement(cq.cinemaName);
            cinemacbox.addItem(cq.cinemaName);
        }
    }
    
    private void GetSessionData()
    {
        GetMovieData();
        GetCinemaData();
        dispType = TableType.SessionTable;
        model.removeAllElements();
        displayableData.clear();
        displayableData = Controller.DirectSearch(new SessionQuery("SELECT * FROM SessionTable"));
        
        for(int i = 0; i < displayableData.size();i++)
        {
            SessionQuery sq = (SessionQuery) displayableData.get(i);
            model.addElement("Показ \'" + sq.movieName + "\' в " + sq.cinemaName + "("+sq.sessionDate+" "+sq.sessionStart+")");
        }
    }
    
    
    private String CheckInput()
    {        
        switch(dispType)
        {
            case MoviesTable: 
            {
                if("".equals(fmovieName.getText()) || fmovieName.getText().contains(";"))
                    return "Поле названия не может быть пустым или содержать недопустимые знаки";
                
                if("".equals(fgenre.getText()) || fgenre.getText().contains(";"))
                    return "Поле жанра не может быть пустым или содержать недопустимые знаки";
                
                if("".equals(fcountry.getText()) || fcountry.getText().contains(";"))
                    return "Поле страны не может быть пустым или содержать недопустимые знаки";
                
                if("".equals(fdirector.getText()) || fdirector.getText().contains(";"))
                    return "Поле режиссера не может быть пустым или содержать недопустимые знаки";
                                   
                try
                {
                    Float.parseFloat(fduration.getText().replace(',', '.'));
                    Float.parseFloat(frating.getText().replace(',', '.'));
                }
                catch(Exception ex)
                {
                    return "Неправильно введены числовые параметры (длительность или рейтинг)";
                }
                
                if("".equals(fdescription.getText()))
                    return "Поле описания не может быть пустым";
                
                if(actorsModel.isEmpty())
                    return "Список актеров не может быть пустым";
                
                break;
            }
            case CinemaTable:
            {
                            
                if("".equals(cinemaName.getText()) || cinemaName.getText().contains(";"))
                    return "Название кинотеатра не может быть пустым или содержать недопустимые знаки";
                
                if("".equals(address.getText()) || address.getText().contains(";"))
                    return "Адрес не может быть пустым";
                
                if("".equals(note.getText()))
                    return "Поле заметка не может быть пустым";
                
                if("".equals(phone.getText()) || phone.getText().contains(";"))
                    return "Поле телефона не может быть пустым или содержать недопустимые знаки";
                

                break;
            }
            case SessionTable:
            {
                if(moviecbox.getSelectedIndex() < 0)
                    return "Фильм должен быть выбран";
                
                if(cinemacbox.getSelectedIndex() < 0)
                    return "Кинотеатр должен быть выбран";
                
                if(date.getDate() == null)
                    return "Не выбрана дата показа";
                
                if(time.getTime() == null)
                    return "Не выбрано время показа";

                break;
            }
        }
        
        return null;
    }
    
    private void SendAddQuery()
    {
        switch(dispType)
        {
            case MoviesTable: 
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "MoviesTable";
                dq.data = new String[9];
                
                dq.data[0] = fmovieName.getText();
                dq.data[1] = fgenre.getText();
                dq.data[2] = String.valueOf(fyear.getValue());
                dq.data[3] = fcountry.getText();
                dq.data[4] = fduration.getText().replace(',', '.');
                dq.data[5] = fdirector.getText();
                
                dq.data[6] = "";
                for(int i = 0; i < actorsModel.getSize(); i++)
                {
                    dq.data[6] += actorsModel.getElementAt(i) + ",";
                }
                dq.data[6] = dq.data[6].substring(0, dq.data[6].length()-1);
                
                dq.data[7] = fdescription.getText();
                dq.data[8] = frating.getText().replace(',','.');
                
                Controller.AddQuery(dq);
                
                GetMovieData();
                
                break;
            }
            case CinemaTable:
            {
                //<editor-fold>
                DataQuery dq = new DataQuery();
                dq.tableName = "CinemaTable";
                dq.data = new String[5];
                
                dq.data[0] = cinemaName.getText();
                dq.data[1] = address.getText();
                dq.data[2] = phone.getText();
                dq.data[3] = halls.getValue().toString();
                dq.data[4] = note.getText();
                
                Controller.AddQuery(dq);
                GetCinemaData();
                //</editor-fold>
                break;
            }
            case SessionTable:
            {
                //<editor-fold>
                DataQuery dq = new DataQuery();
                dq.tableName = "SessionTable";
                dq.data = new String[7];
                
                dq.data[0] = moviecbox.getSelectedItem().toString();
                dq.data[1] = cinemacbox.getSelectedItem().toString();
                dq.data[2] = date.getDate().toString();
                dq.data[3] = time.getTime().toString();
                dq.data[4] = minprice.getValue().toString();
                dq.data[5] = maxprice.getValue().toString();
                
                if(booking.isSelected())
                    dq.data[6] = "true";
                else
                    dq.data[6] = "false";
                
                Controller.AddQuery(dq);
                GetSessionData();
                //</editor-fold>
                break;
            }
        }
    }
    
    private void SendDeleteQuery()
    {
        switch(dispType)
        {
            case MoviesTable: 
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "MoviesTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id; 
                Controller.DeleteQuery(dq);
                GetMovieData();
                
                break;
            }
            case CinemaTable:
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "CinemaTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id; 
                Controller.DeleteQuery(dq);
                GetCinemaData();

                break;
            }
            case SessionTable:
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "SessionTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id; 
                Controller.DeleteQuery(dq);
                GetSessionData();

                break;
            }
        }
    }
    
    private void SendUpdateQuery()
    {
        switch(dispType)
        {
            case MoviesTable: 
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "MoviesTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id;
                dq.data = new String[9];
                
                dq.data[0] = fmovieName.getText();
                dq.data[1] = fgenre.getText();
                dq.data[2] = String.valueOf(fyear.getValue());
                dq.data[3] = fcountry.getText();
                dq.data[4] = fduration.getText().replace(',', '.');
                dq.data[5] = fdirector.getText();
                
                dq.data[6] = "";
                for(int i = 0; i < actorsModel.getSize(); i++)
                {
                    dq.data[6] += actorsModel.getElementAt(i) + ",";
                }
                dq.data[6] = dq.data[6].substring(0, dq.data[6].length()-1);
                
                dq.data[7] = fdescription.getText();
                dq.data[8] = frating.getText().replace(',','.');
                
                Controller.UpdateQuery(dq);
                
                GetMovieData();
                
                break;
            }
            case CinemaTable:
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "CinemaTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id;
                dq.data = new String[5];
                
                dq.data[0] = cinemaName.getText();
                dq.data[1] = address.getText();
                dq.data[2] = phone.getText();
                dq.data[3] = halls.getValue().toString();
                dq.data[4] = note.getText();
                
                Controller.UpdateQuery(dq);
                GetCinemaData();

                break;
            }
            case SessionTable:
            {
                DataQuery dq = new DataQuery();
                dq.tableName = "SessionTable";
                dq.id = displayableData.get(recordlist.getSelectedIndex()).id;
                dq.data = new String[7];
                
                dq.data[0] = moviecbox.getSelectedItem().toString();
                dq.data[1] = cinemacbox.getSelectedItem().toString();
                dq.data[2] = date.getDate().toString();
                dq.data[3] = time.getTime().toString();
                dq.data[4] = minprice.getValue().toString();
                dq.data[5] = maxprice.getValue().toString();
                
                if(booking.isSelected())
                    dq.data[6] = "true";
                else
                    dq.data[6] = "false";
                
                Controller.UpdateQuery(dq);
                GetSessionData();
                
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        Movies = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fmovieName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fgenre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fyear = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        fcountry = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        fduration = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fdirector = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        newactor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        actorsList = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        frating = new javax.swing.JTextField();
        jLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fdescription = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        Cinemas = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cinemaName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        address = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        phone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        halls = new javax.swing.JSpinner();
        jScrollPane5 = new javax.swing.JScrollPane();
        note = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        Sessions = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        moviecbox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cinemacbox = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        date = new com.github.lgooddatepicker.components.DatePicker();
        time = new com.github.lgooddatepicker.components.TimePicker();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        minprice = new javax.swing.JSpinner();
        maxprice = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        booking = new javax.swing.JCheckBox();
        accountName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordlist = new javax.swing.JList<>();
        delBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        updBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Панель администратора");
        setName("AdminPanel"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("Администрирование");

        jLabel2.setText("Название");

        fmovieName.setColumns(1);
        fmovieName.setMaximumSize(new java.awt.Dimension(7, 20));

        jLabel3.setText("Жанр");

        jLabel4.setText("Год выпуска");

        fyear.setModel(new javax.swing.SpinnerNumberModel(1896, 1896, null, 1));

        jLabel5.setText("Страна");

        jLabel6.setText("Прод-ть");

        jLabel7.setText("Режиссер");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(actorsList);

        jLabel8.setText("Рейтинг");

        jLabel.setText("Актеры");

        fdescription.setColumns(20);
        fdescription.setLineWrap(true);
        fdescription.setRows(5);
        fdescription.setWrapStyleWord(true);
        jScrollPane2.setViewportView(fdescription);

        jLabel9.setText("Описание");

        javax.swing.GroupLayout MoviesLayout = new javax.swing.GroupLayout(Movies);
        Movies.setLayout(MoviesLayout);
        MoviesLayout.setHorizontalGroup(
            MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviesLayout.createSequentialGroup()
                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MoviesLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MoviesLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(fyear, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MoviesLayout.createSequentialGroup()
                                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MoviesLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6)))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(fduration, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fgenre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fdirector)
                                        .addComponent(fmovieName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(fcountry, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(MoviesLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(frating, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel)
                    .addGroup(MoviesLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newactor, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(MoviesLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(22, 22, 22))
        );
        MoviesLayout.setVerticalGroup(
            MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MoviesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fmovieName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MoviesLayout.createSequentialGroup()
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(fgenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fcountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fduration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fdirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(26, 26, 26)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(frating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(MoviesLayout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MoviesLayout.createSequentialGroup()
                                .addComponent(newactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(MoviesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabs.addTab("Таблица фильмов", Movies);

        jLabel10.setText("Название кинотеатра");

        jLabel11.setText("Адрес");

        address.setColumns(20);
        address.setLineWrap(true);
        address.setRows(5);
        address.setWrapStyleWord(true);
        jScrollPane4.setViewportView(address);

        jLabel12.setText("Телефон");

        jLabel13.setText("Число залов");

        halls.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        note.setColumns(20);
        note.setLineWrap(true);
        note.setRows(5);
        note.setWrapStyleWord(true);
        jScrollPane5.setViewportView(note);

        jLabel14.setText("Заметка");

        javax.swing.GroupLayout CinemasLayout = new javax.swing.GroupLayout(Cinemas);
        Cinemas.setLayout(CinemasLayout);
        CinemasLayout.setHorizontalGroup(
            CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CinemasLayout.createSequentialGroup()
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CinemasLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(cinemaName, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(CinemasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(halls, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(220, 220, 220))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CinemasLayout.createSequentialGroup()
                        .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CinemasLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CinemasLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane5))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        CinemasLayout.setVerticalGroup(
            CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CinemasLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cinemaName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(halls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(CinemasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGroup(CinemasLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabs.addTab("Таблица кинотеатров", Cinemas);

        jLabel15.setText("Название фильма");

        moviecbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Название кинотеатра");

        cinemacbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Дата сеанса");

        jLabel18.setText("Время сеанса");

        jLabel19.setText("Минимальная цена");

        jLabel20.setText("Максимальная цена");

        minprice.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel21.setText("Возможность бронирования");

        javax.swing.GroupLayout SessionsLayout = new javax.swing.GroupLayout(Sessions);
        Sessions.setLayout(SessionsLayout);
        SessionsLayout.setHorizontalGroup(
            SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SessionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SessionsLayout.createSequentialGroup()
                        .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(moviecbox, 0, 187, Short.MAX_VALUE)
                            .addComponent(cinemacbox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(SessionsLayout.createSequentialGroup()
                        .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(SessionsLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SessionsLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(booking))
                            .addGroup(SessionsLayout.createSequentialGroup()
                                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(maxprice)
                                    .addComponent(minprice, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))))))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        SessionsLayout.setVerticalGroup(
            SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SessionsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(moviecbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cinemacbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(37, 37, 37)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(minprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(SessionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SessionsLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SessionsLayout.createSequentialGroup()
                        .addComponent(booking)
                        .addGap(36, 36, 36))))
        );

        tabs.addTab("Таблица сеансов", Sessions);

        accountName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        accountName.setText("Администратор:");

        recordlist.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(recordlist);

        delBtn.setText("УДАЛИТЬ");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        addBtn.setText("ДОБАВИТЬ");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        updBtn.setText("ОБНОВИТЬ");
        updBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(350, 350, 350))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(accountName)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(accountName)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        UIController.AdminPanelExit();
    }//GEN-LAST:event_formWindowClosed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if(newactor.getText() != "")
        {
            actorsModel.addElement(newactor.getText());
            newactor.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(actorsList.getSelectedIndex() >= 0)
        {
            actorsModel.remove(actorsList.getSelectedIndex());
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
        if(recordlist.getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Действие невозможно - строка не выбрана", "Ошибка", 2);
        }
        else
        {
            SendDeleteQuery();
        }
    }//GEN-LAST:event_delBtnActionPerformed

    private void updBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updBtnActionPerformed
        if(recordlist.getSelectedIndex() < 0)
        {
            JOptionPane.showMessageDialog(rootPane, "Действие невозможно - строка не выбрана", "Ошибка", 2);
        }
        else
        {
            String ch = CheckInput();
            if(ch == null)
            {
                SendUpdateQuery();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, ch, "Ошибка", 2);
            }
        }
    }//GEN-LAST:event_updBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String ch = CheckInput();
        if(ch == null)
        {
            SendAddQuery();
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, ch, "Ошибка", 2);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cinemas;
    private javax.swing.JPanel Movies;
    private javax.swing.JPanel Sessions;
    private javax.swing.JLabel accountName;
    private javax.swing.JList<String> actorsList;
    private javax.swing.JButton addBtn;
    private javax.swing.JTextArea address;
    private javax.swing.JCheckBox booking;
    private javax.swing.JTextField cinemaName;
    private javax.swing.JComboBox<String> cinemacbox;
    private com.github.lgooddatepicker.components.DatePicker date;
    private javax.swing.JButton delBtn;
    private javax.swing.JTextField fcountry;
    private javax.swing.JTextArea fdescription;
    private javax.swing.JTextField fdirector;
    private javax.swing.JTextField fduration;
    public javax.swing.JTextField fgenre;
    public javax.swing.JTextField fmovieName;
    private javax.swing.JTextField frating;
    private javax.swing.JSpinner fyear;
    private javax.swing.JSpinner halls;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSpinner maxprice;
    private javax.swing.JSpinner minprice;
    private javax.swing.JComboBox<String> moviecbox;
    private javax.swing.JTextField newactor;
    private javax.swing.JTextArea note;
    private javax.swing.JTextField phone;
    private javax.swing.JList<String> recordlist;
    private javax.swing.JTabbedPane tabs;
    private com.github.lgooddatepicker.components.TimePicker time;
    private javax.swing.JButton updBtn;
    // End of variables declaration//GEN-END:variables
}
