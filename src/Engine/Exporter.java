package Engine;

import java.time.LocalDate;
import java.util.ArrayList;



public class Exporter 
{
    private String answer;
    
    public Exporter(String date)
    {
        answer = "";
        String all = "SELECT COUNT(*) FROM SessionTable WHERE SessionDate = \'" + date+"\'";
        
        answer += "Общее число фильмов на " +date+": "+ Database.ExecuteSingle(all) +"\n";
        
        ArrayList<String> genres = Database.SelectOptions("SELECT DISTINCT Genre FROM MoviesTable");
        ArrayList<String> countries = Database.SelectOptions("SELECT DISTINCT Country FROM MoviesTable");
        ArrayList<String> cinemas = Database.SelectOptions("SELECT DISTINCT CinemaName FROM CinemaTable");
        answer += "Общая статистика\n";
        answer += "\tДля каждого жанра: \n";
        for(int i = 0; i < genres.size(); i++)
        {
            answer += genres.get(i) + ": " + Database.ExecuteSingle("SELECT COUNT(*) FROM MoviesTable WHERE Genre = \'" +genres.get(i)+"\'")+"\n";
        }
        
        answer += "\tДля каждой страны: \n";
        for(int i = 0; i < countries.size(); i++)
        {
            answer += countries.get(i) + ": " + Database.ExecuteSingle("SELECT COUNT(*) FROM MoviesTable WHERE Country = \'" +countries.get(i)+"\'")+"\n";
        }
        
        answer += "\t\tДля каждого кинотеатра: \n";
        for(int i = 0; i < cinemas.size(); i++)
        {
            answer += "\tКинотеатр: " + cinemas.get(i) + "\n";
            answer += "Число фильмов: " + Database.ExecuteSingle("SELECT COUNT(DISTINCT(MovieName)) FROM SessionTable WHERE CinemaName = \'" +cinemas.get(i)+"\'")+"\n";
            answer += "Число сеансов: " + Database.ExecuteSingle("SELECT COUNT(*) FROM SessionTable WHERE CinemaName = \'"+ cinemas.get(i)+"\'")+"\n";
        }
    }
    
    
    public String GetExportAnswer()
    {
        return answer;
    }
    
}
