package Query;

public class DataQuery 
{
    public int id;
    public String tableName;
    public String[] data;
    
    private String[] forbiddensymbols = new String[] {";"};
    
    public void Secure()
    {
        for(int i = 0; i < data.length;i++)
        {
            for(int j = 0; j < forbiddensymbols.length;j++)
            {
                data[i] = data[i].replace(forbiddensymbols[j], "");
            }
        }
    }
}
