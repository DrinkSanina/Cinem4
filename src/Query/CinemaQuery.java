package Query;

public class CinemaQuery extends SearchQuery
{
    
    public String cinemaName;
    public String address;
    public String phoneNumber;
    public int amountOfHalls;
    public String note;
    
    public CinemaQuery()
    {
        type = TableType.CinemaTable;
        SQLQueryText = "SELECT * FROM CinemaTable";
    }
    
    public CinemaQuery(String queryText)
    {
        type = TableType.CinemaTable;
        SQLQueryText = queryText;
    }
    
    @Override
    public void TranslateResultString(String str)
    {
        String[] array = str.split(";");
        
        id = Integer.parseInt(array[0]);
        cinemaName = array[1];
        address = array[2];
        phoneNumber = array[3];
        amountOfHalls = Integer.parseInt(array[4]);
        note = array[5];
    }
}
