package Query;

public abstract class SearchQuery 
{
    public int id;
    public enum TableType {CinemaTable, MoviesTable, SessionTable};
    
    public TableType type;
    public String SQLQueryText;
    public abstract void TranslateResultString(String str);
}
