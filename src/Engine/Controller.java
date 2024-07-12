package Engine;
import Query.*;
import Shell.UIController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Controller 
{
    private static boolean db_status;
    
    public static void main(String[] args)
    {
        UIController.ShowForm();
        db_status = ConnectToDB();
        UIController.SetConnectionStatus(db_status);
    }
    
    //Подключиться к базе данных или решить, что это невозможно
    private static boolean ConnectToDB()
    {
        try
        {
            Database.Connect();
        }
        catch(SQLException e)
        {
            return false;
        }
        return true;
    }
    
    //Запустить алгоритм поиска базы данных
    public static void InitSearch(SearchQuery sq)
    {
        ArrayList<SearchQuery> DBAnswer = Database.SelectRecords(sq);
        if(DBAnswer == null || DBAnswer.isEmpty())
            UIController.DisplayMessage("По вашему запросу ничего не найдено");
        else
            UIController.DisplayResult(DBAnswer);
    }
    //Запустить простой неотслеживамый запрос
    public static ArrayList<SearchQuery> DirectSearch(SearchQuery sq)
    {
        return Database.SelectRecords(sq);
    }
    
    //Авторизировать пользователя или вывести сообщение об ошибке
    public static void HandleLogin(Account acc)
    {
        Account DBAnswer = Database.LoginAccount(acc);       
        if(DBAnswer == null)
            UIController.LoginFormError();
        else
            UIController.DisplayAccount(DBAnswer);
    }
    
    
    public static ArrayList<String> SelectOptions(String query)
    {
        return Database.SelectOptions(query);
    }
    
    //Запросы администратора, изменяющие таблицы
    public static void UpdateQuery(DataQuery dq)
    {
        dq.Secure();
        Database.UpdateQuery(dq);
    }
    public static void AddQuery(DataQuery dq)
    {
        dq.Secure();
        Database.AddQuery(dq);
    }
    public static void DeleteQuery(DataQuery dq)
    {
        Database.DeleteQuery(dq);
    }
    
    //Подготовить информацию для экспорта и отобразить её на экране
    public static void PrepareExport(String date)
    {
        Exporter exprt = new Exporter(date);
        UIController.DisplayExportData(exprt);
    }
    
}
