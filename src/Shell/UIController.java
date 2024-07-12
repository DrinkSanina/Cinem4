package Shell;

import Query.MovieQuery;
import Query.SessionQuery;
import Query.SearchQuery;
import Query.CinemaQuery;
import Engine.Controller;
import Engine.Account;
import Engine.Exporter;
import Shell.Forms.*;
import Shell.Forms.Filters.*;
import Shell.Forms.Records.*;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoDeepOceanIJTheme;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class UIController 
{
    private static UserInterface application;
    private static LoginForm loginform;
    private static Account user;
    private static Output mesdisplay;
    private static AdminPanel adminPanel;
    private static ArrayList<JPanel> navigators;
    
    private static boolean connection;
    public static void ShowForm()
    {
        //Задать особый внешний вид
        FlatGradiantoDeepOceanIJTheme.setup();
        
        //Отобразить форму на экране и скрыть админ-панель
        application = new UserInterface();
        application.setVisible(true);
        application.AdminMenu.setVisible(false);
        GreetingWindow();
        
        //Упрощение управления панелями
        navigators = new ArrayList<JPanel>();
        navigators.add(application.MoviesPanel);
        navigators.add(application.CinemasPanel);
        navigators.add(application.SessionsPanel);
        
        //Вставка логотипа и значка программы
        application.setIconImage(Toolkit.getDefaultToolkit().getImage("clapper5.png"));
        application.iconLabel.setIcon(new ImageIcon("logo.png"));
    }
    
    //Выставляет небольшой стаус подключения в правом нижнем углу программы
    public static void SetConnectionStatus(boolean status)
    {
        connection = status;
        if(connection)
        {
            application.con_status.setText("Связь с БД: получена");
            application.con_status.setForeground(Color.green);
        }
        else
        {
            application.con_status.setText("Связь с БД: отсутствует");
            application.con_status.setForeground(Color.red);
        }
    }
    
    private static void GreetingWindow()
    {
        Output init = new Output();
        
        init.Message.setText("Для начала работы выберите отображаемую таблицу слева\n"
                + "Фильтры будут загружаться и меняться динамически в зависимости от выбранной таблицы\n"
                + "Для входа в аккаунт администратрора, воспользуйтесь панелью авторизации");
        
        AddContentComponent(init, 0);
        ContentPanelResize();
    }
    
    
    
    //Генерация контента на панели
    public static void DisplayResult(ArrayList<SearchQuery> result)
    {
        application.ContentPanel.removeAll();
        int y_point = 0;
        for(int i = 0; i < result.size();i++)
        {
            switch(result.get(i).type)
            {
                case CinemaTable:
                {
                    CinemaRecord cr = new CinemaRecord();
                    CinemaQuery cq = (CinemaQuery) result.get(i);
                    cr.id.setText(String.valueOf(cq.id));
                    cr.name.setText(cq.cinemaName);
                    cr.address.setText(cq.address);
                    cr.phoneNumber.setText(cq.phoneNumber);
                    cr.note.setText(cq.note);
                    cr.amountOfHalls.setText(String.valueOf(cq.amountOfHalls));
                    AddContentComponent(cr, y_point);
                    y_point += cr.getPreferredSize().height;
                    break;
                }
                case MoviesTable:
                {
                    //<editor-fold>
                    MovieRecord mr = new MovieRecord();
                    MovieQuery mq = (MovieQuery) result.get(i);
                    
                    mr.id.setText(String.valueOf(mq.id));
                    mr.name.setText(mq.movieName);
                    mr.Genre.setText(mq.genre);
                    mr.Year.setText(String.valueOf(mq.year));
                    mr.Country.setText(mq.country);
                    mr.Duration.setText(String.valueOf(mq.duration));
                    mr.Director.setText(mq.director);
                    mr.DescriptionArea.setText(mq.description);
                    mr.Rating.setText(String.valueOf(mq.rating));
                    for(int j = 0; j < mq.actors.size();j++)
                    {
                        mr.Actors.append(mq.actors.get(j) + "\n");
                    }
                    AddContentComponent(mr, y_point);
                   
                    //</editor-fold>
                    break;
                }
                case SessionTable:
                {
                    //<editor-fold>
                    SessionRecord sr = new SessionRecord();
                    SessionQuery sq = (SessionQuery) result.get(i);
                    sr.id.setText(String.valueOf(sq.id));
                    sr.MovieName.setText(sq.movieName);
                    sr.CinemaName.setText(sq.cinemaName);
                    sr.Date.setText(sq.sessionDate);
                    sr.Time.setText(sq.sessionStart);
                    sr.MinP.setText(String.valueOf(sq.minPrice));
                    sr.MaxP.setText(String.valueOf(sq.maxPrice));
                    sr.booking.setSelected(sq.booking);
                    AddContentComponent(sr, y_point);
                    y_point += sr.getPreferredSize().height;
                    //</editor-fold>
                    break;
                }
            }
        }
        ContentPanelResize();
    }
    
    public static void DisplayMessage(String message)
    {
        application.ContentPanel.removeAll();
        Output init = new Output();
        init.Message.setText(message);
        AddContentComponent(init, 0);
        ContentPanelResize();
    }    
    public static void DisplayAccount(Account acc)
    {
        loginform.dispose();
        user = acc;
        
        application.exitMenu.setEnabled(true);
        application.loginMenu.setEnabled(false); 
        
        if(user.isAdmin)
        {
            application.AdminMenu.setVisible(true);
        }        
    }
    
    private static void ContentPanelResize()
    {
        Component[] comps = application.ContentPanel.getComponents();
        int componentsHeight = 0;
        for(int i = 0; i < comps.length;i++)
        {
            componentsHeight += comps[i].getHeight();
        }
        
        if(componentsHeight <= 500)
            application.ContentPanel.setPreferredSize(new Dimension(1046,500));
        else
            application.ContentPanel.setPreferredSize(new Dimension(application.ContentPanel.getWidth(), componentsHeight));
        
        application.ContentPanel.repaint();
        application.ContentPanel.revalidate();        
        application.Scroll.revalidate();
        application.Scroll.repaint();
    }
    
    public static void AddContentComponent(Component record, int y_point)
    {
        record.setSize(record.getPreferredSize());
        record.setBounds(0, y_point, record.getWidth(), record.getHeight());
        application.ContentPanel.add(record);
    }
    
    public static void ChangeFilter(Component filter)
    {
        filter.setSize(filter.getPreferredSize());
        filter.setBounds(0,0,filter.getWidth(),filter.getHeight());
        
        application.FilterPanel.repaint();
        application.FilterPanel.removeAll();
        application.FilterPanel.add(filter);
        application.revalidate();
    }
    
    private static void NavigatorsSolve(int switchingIndex)
    {
        if(switchingIndex < 0)
        {
            for(int i = 0; i < navigators.size();i++)
            {
                navigators.get(i).setEnabled(true);
                navigators.get(i).setBackground(new Color(29, 46, 56));
            }
            return;
        }
        
        for(int i = 0; i < navigators.size(); i++)
        {
            if(i == switchingIndex)
            {
                navigators.get(i).setEnabled(false);
                navigators.get(i).setBackground(new Color(41, 58, 86));
            }
            else
            {
                navigators.get(i).setEnabled(true);
                navigators.get(i).setBackground(new Color(29, 46, 56));
            }
        }
    }
    
    private static void ClearApplication()
    {
        NavigatorsSolve(-1);
        application.ContentPanel.removeAll();
        application.ContentPanel.repaint();
        application.ContentPanel.revalidate();
        
        application.FilterPanel.removeAll();
        application.FilterPanel.revalidate();
        application.FilterPanel.repaint();
    }
    //
    
    public static void ShowExportPanel()
    {
        ClearApplication();
        mesdisplay = new Output();
        mesdisplay.ShowExportPanel();
        mesdisplay.Message.setText("Выберите дату и статистические данные отобразятся на экране\n"
                + "\tОбщее число демонстриуемых фильмов\n"
                + "\tОбщее число демонстриуемых фильмов для каждого жанра\n"
                + "\tОбщее число демонстриуемых фильмов для каждой страны\n"
                + "\tОбщее число демонстриуемых фильмов и сеансов для каждого кинотеатра\n");
        AddContentComponent(mesdisplay, 0);
        ContentPanelResize();
    }
    
    public static void HandleExport(String date)
    {
        Controller.PrepareExport(date);
    }
    
    public static void DisplayExportData(Exporter obj)
    {
        mesdisplay.exportedData.setText(obj.GetExportAnswer());
    }
     //Обработка ввода
    
    public static void MoviePanelClick()
    {
        if(!connection)
        {
            DisplayMessage("Отсутствует соединение с базой данных");
            return;
        }
        NavigatorsSolve(0);
        MovieQuery mq = new MovieQuery();
        Controller.InitSearch(mq);
        ChangeFilter(new MovieFilter());
    }
    public static void CinemaPanelClick()
    {
        if(!connection)
        {
            DisplayMessage("Отсутствует соединение с базой данных");
            return;
        }
        NavigatorsSolve(1);
        CinemaQuery cq = new CinemaQuery();
        Controller.InitSearch(cq);
        ChangeFilter(new CinemaFilter());
    }
    
    public static void SessionPanelClick()
    {
        if(!connection)
        {
            DisplayMessage("Отсутствует соединение с базой данных");
            return;
        }
        NavigatorsSolve(2);
        SessionQuery sq = new SessionQuery();
        Controller.InitSearch(sq);
        ChangeFilter(new SessionFilter());
    }
    
    public static void LoginForm()
    {
        application.loginMenu.setEnabled(false);
        loginform = new LoginForm();
        loginform.setVisible(true);
        
        loginform.setIconImage(Toolkit.getDefaultToolkit().getImage("padlock.png"));
    }
    
    public static void LoginFormExit()
    {
        if(user == null)
            application.loginMenu.setEnabled(true);
    }
    
    public static void LoginFormError()
    {
        loginform.ShowError();
    }
    
    public static void ExitAccount()
    {
        user = null;
        application.AdminMenu.setVisible(false);
        application.loginMenu.setEnabled(true);
        application.exitMenu.setEnabled(false);
    }
    
    public static void LoginCheck(Account input)
    {
        Controller.HandleLogin(input);
    }
    
    //Отслеживание фильтров
    public static void AcceptFilter(SearchQuery filterdata)
    {
        Controller.InitSearch(filterdata);
    }
    public static void CheckCinemaSessions(String cinemaName)
    {
        NavigatorsSolve(2);
        SessionFilter sf = new SessionFilter();
        ChangeFilter(sf);
        sf.AcceptCinemaLink(cinemaName);
    }
    public static void CheckMovieSessions(String movieName)
    {
        NavigatorsSolve(2);
        SessionFilter sf = new SessionFilter();
        ChangeFilter(sf);
        sf.AcceptMovieLink(movieName);
    }
    public static void CheckMovie(String movieName)
    {
        NavigatorsSolve(0);
        MovieFilter mf = new MovieFilter();
        ChangeFilter(mf);
        mf.AcceptSessionLink(movieName);
    }
    public static void CheckCinema(String cinemaName)
    {
        NavigatorsSolve(1);
        CinemaFilter cf = new CinemaFilter();
        ChangeFilter(cf);
        cf.AcceptSessionLink(cinemaName);
    }
    //
    
    //Панель администратора
    public static void Administration()
    {
        adminPanel = new AdminPanel(user.login);
        adminPanel.setVisible(true);
        adminPanel.setIconImage(Toolkit.getDefaultToolkit().getImage("cogwheel.png"));
        application.setEnabled(false);
        ClearApplication();
        DisplayMessage("Администрирование базы данных в процессе...");
    }
    
    public static void AdminPanelExit()
    {
        application.toFront();
        application.setEnabled(true);
        DisplayMessage("Администрирование завершено. Можно продолжать работу в обычном режиме");
    }
    //
}
