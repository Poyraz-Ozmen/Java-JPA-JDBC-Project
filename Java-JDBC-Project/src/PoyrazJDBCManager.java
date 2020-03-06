import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
import com.mysql.cj.xdevapi.Statement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PoyrazJDBCManager {
	
	// readFromFile and fill the array
	public static ArrayList<Country> readFromFile(String fileName)  {
		ArrayList<Country> listCountry = new ArrayList<Country>();
		Scanner in;
		try {
			in = new Scanner (new File(fileName));
			while (in.hasNext()) {
				String line = in.nextLine();
				String[] arr = line.split(",");
				int popTemp = Integer.parseInt(arr[3]);
				Country c = new Country(arr[0], arr[1], arr[2], popTemp); // public Country(String name, String continent, String capital, int population)
				listCountry.add(c);
				//System.out.println(c.getID()); For test
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	return listCountry;
	}
	
	public static void writeIntoTable(ArrayList<Country> listCountry) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "poyraz");	
			for (Country country : listCountry)
			{
				PreparedStatement ps =  connection.prepareStatement("insert into country (id, name,continent,capital,population) values (?,?,?,?,?) ");
				ps.setInt(1, country.getID());
				ps.setString(2, country.getName());
				ps.setString(3, country.getContinent());
				ps.setString(4, country.getCapital());
				ps.setInt(5, country.getPopulation());
				ps.execute();
			}
			
			
			System.out.println("Succesful Insertion");
		}
		catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("Duplicate entry.");
		}
		catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	
	public static Country getCountryByID (int id) {
		Country tempCountryObject = new Country();
		try
	    {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "poyraz");
	      
	       
	        String query = "select * from country where id = " + id;
	        
	        
	        java.sql.Statement statement = con2.createStatement();
	      
	  	    ResultSet ResultSet = statement.executeQuery(query);
	        
	        
	        while (ResultSet.next())
	        {
	        	int tempID = ResultSet.getInt("id");
	        	String name = ResultSet.getString("name");
	        	String continent = ResultSet.getString("continent");
	        	String capital = ResultSet.getString("capital");
	        	int pop = ResultSet.getInt("population");
	        	tempCountryObject = new Country(name, continent, capital, pop);
	        	tempCountryObject.setId(tempID);
	      }
	      statement.close();
	      
	    }
	    catch (Exception e)
	    {
	      System.err.println("Exception");
	      System.err.println(e.getMessage());
	    }
		return tempCountryObject;
	}

    public static boolean deleteCountryById(Integer id) {

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "poyraz");

            PreparedStatement preparedStatement =  con3.prepareStatement("delete from country where id = ?");
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();

            con3.close();

        }

        catch(Exception e){

            System.out.println(e);
            e.printStackTrace();

            return false;

        }

        return true;
    }
    
	public static void updateCountryPopulationByID (int id, int population) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con4 = DriverManager.getConnection("jdbc:mysql://localhost/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "poyraz");	
			
			PreparedStatement ps =  con4.prepareStatement("update country set population = ? where id = ?");
			
			ps.setInt(1,population);
			ps.setInt(2,id);
			
			ps.executeUpdate();	
		
		}
		catch (Exception e) {
			e.printStackTrace();
		};
	}


}
