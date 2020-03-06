import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, SQLException, ClassNotFoundException {
		
		
		String filename = "world.txt";
		ArrayList<Country> listCountry = PoyrazJDBCManager.readFromFile(filename);

		PoyrazJDBCManager.writeIntoTable(listCountry); // successfull passed
													// execute this statement once otherwise it will try to entry countries to db, they already exist. Duplicate error
		
		Country tempCountryObject = new Country(); 
		tempCountryObject = PoyrazJDBCManager.getCountryByID(3); // number 3 is Algeria
		System.out.println(tempCountryObject.getName()); //Successfull passed.
		
		PoyrazJDBCManager.deleteCountryById(4);
		System.out.println("Country deleted which is countryID=4"); // successfull passed ,country 4 is deleted Andorra 
		
		
		PoyrazJDBCManager.updateCountryPopulationByID(7,100); //Successfull passed, number 7 is Argentina now its pop is 100 
	}
		
}

