package Query;


public class SessionQuery extends SearchQuery
{
    
    public String movieName;
    public String cinemaName;
    public String sessionDate;
    public String sessionStart;
    public float minPrice;
    public float maxPrice;
    public boolean booking;
    
    
    public SessionQuery()
    {
        type = TableType.SessionTable;
        SQLQueryText = "SELECT * FROM SessionTable";
    }
    
    public SessionQuery(String queryText)
    {
        type = TableType.SessionTable;
        SQLQueryText = queryText;
    }
    
    @Override
    public void TranslateResultString(String str)
    {
        String[] array = str.split(";");
        id = Integer.parseInt(array[0]);
        movieName = array[1];
        cinemaName = array[2];
        sessionDate = array[3];
        sessionStart = array[4];
        minPrice = Float.parseFloat(array[5]);
        maxPrice = Float.parseFloat(array[6]);        
        booking = Boolean.parseBoolean(array[7]);
    }
}
