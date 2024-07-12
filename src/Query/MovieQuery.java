package Query;
import java.util.ArrayList;

public class MovieQuery extends SearchQuery
{
    
    public String movieName;
    public String genre;
    public int year;
    public String country;
    public float duration;
    public String director;
    public ArrayList<String> actors;
    public String description;
    public float rating;
    
    public MovieQuery()
    {
        type = TableType.MoviesTable;
        SQLQueryText = "SELECT * FROM MoviesTable";
    }
    
    public MovieQuery(String queryText)
    {
        type = TableType.MoviesTable;
        SQLQueryText = queryText;
    }
    
    @Override
    public void TranslateResultString(String str)
    {
        String[] array = str.split(";");
        id = Integer.parseInt(array[0]);
        movieName = array[1];
        genre = array[2];
        year = Integer.parseInt(array[3]);
        country =array[4];
        duration = Float.parseFloat(array[5]);
        director = array[6];
        actors = new ArrayList<String>();
        if(array[7].contains(","))
        {
            String[] spl = array[7].split(",");
            for(int i = 0; i < spl.length; i++)
                actors.add(spl[i]);
        }
        else
            actors.add(array[7]);
        description = array[8];
        rating = Float.parseFloat(array[9]);
    }
}
