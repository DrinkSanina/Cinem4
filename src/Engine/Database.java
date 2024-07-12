package Engine;
import Query.*;
import org.sqlite.JDBC;
import java.sql.*;
import java.util.ArrayList;


public class Database 
{
    //Файл локальной базы данных
    public static String connection_string = "jdbc:sqlite:D:\\sessio.db";
    private static Connection connection;

    //Подключиться к базе данных
    public static void Connect() throws SQLException
    {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(connection_string);
    }
    //Основной поисковый запрос
    public static ArrayList<SearchQuery> SelectRecords(SearchQuery sq)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sq.SQLQueryText);
            int columns = rs.getMetaData().getColumnCount(); 
            ArrayList<SearchQuery> answer = new ArrayList<SearchQuery>();
            while(rs.next())
            {
                String resString = "";
                SearchQuery line = null;
                switch(sq.type)
                {
                    case CinemaTable: line = new CinemaQuery();break;
                    case MoviesTable: line = new MovieQuery();break;
                    case SessionTable: line = new SessionQuery();break;
                }
                for(int i = 1; i <= columns; i++)
                    resString += rs.getString(i)+";";
                
                line.TranslateResultString(resString);
                answer.add(line);
            }
            return answer;
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
            return null;
        }
    }
    
    
    
    public static Account LoginAccount(Account acc)
    {
        try
        {
            String sql = "SELECT * FROM Users where login=? AND password=?";
            PreparedStatement prep = connection.prepareStatement(sql);
            prep.setString(1, acc.login);
            prep.setString(2, acc.password);
            
            ResultSet rs = prep.executeQuery();
            
            Account user = new Account();
            user.login = rs.getString("login");
            user.password = rs.getString("password");
            
            switch(rs.getString("isAdmin"))
            {
                case "true": user.isAdmin = true; break;
                case "false": user.isAdmin = false;break;
            }
            
            return user;
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
            return null;
        }
    }
    
    public static String ExecuteSingle(String query)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs.getString(1);
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
            return null;
        }
    }
    
    public static ArrayList<String> SelectOptions(String query)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ArrayList<String> result = new ArrayList<String>();
            
            while(rs.next())
            {
                result.add(rs.getString(1));
            }
            
            return result;
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
            return null;
        }
    }
    
    public static void UpdateQuery(DataQuery dq)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rsCol = statement.executeQuery("PRAGMA table_info(\'" + dq.tableName + "\')");
            ArrayList<String> columns = new ArrayList<String>();
            
            while(rsCol.next())
            {
                columns.add(rsCol.getString("name"));
            }
            
            String updquery = "UPDATE " + dq.tableName;
            updquery +=" SET";
            
            for(int i = 0; i < dq.data.length;i++)
            {
                updquery += " " + columns.get(i+1) + " = \'" + dq.data[i] + "\',";
            }
            updquery = updquery.substring(0, updquery.length()-1);
            
            updquery += " WHERE id = " + dq.id;
            statement = connection.createStatement();
            
            statement.executeUpdate(updquery);
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
        }
    }
    
    
    public static void AddQuery(DataQuery dq)
    {
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rsCol = statement.executeQuery("PRAGMA table_info(\'" + dq.tableName + "\')");
            ArrayList<String> columns = new ArrayList<String>();
            
            while(rsCol.next())
            {
                columns.add(rsCol.getString("name"));
            }
            
            
            String addquery = "INSERT INTO " + dq.tableName + " (";
            
            for(int i = 1; i < columns.size();i++)
            {
                addquery += columns.get(i) +",";
            }
            addquery = addquery.substring(0, addquery.length()-1) + ")";
            
            addquery +=" VALUES (";
            for(int i = 0; i < dq.data.length;i++)
            {
                addquery += "\'" + dq.data[i] + "\',";
            }
            addquery = addquery.substring(0, addquery.length()-1);
            addquery+= ")";
            statement.executeUpdate(addquery);
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
        }

    }
    
    public static void DeleteQuery(DataQuery dq)
    {
        try
        {
            Statement statement = connection.createStatement();
            String addquery = "DELETE FROM " + dq.tableName + " WHERE id=" +dq.id;
            statement.executeUpdate(addquery);
        }
        catch(Exception ex)
        {
            System.out.println("Failed to execute query");
            System.out.println(ex);
        }
    }
}
