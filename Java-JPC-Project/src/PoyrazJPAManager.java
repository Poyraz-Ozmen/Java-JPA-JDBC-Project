import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PoyrazJPAManager {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
	static EntityManager entityManager =emf.createEntityManager();
	
	
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



	public static void writeIntoTable ( ArrayList<Country> listCountry ) {
		try {
	
			for(Country c: listCountry) {
	
				entityManager.getTransaction().begin();
				entityManager.persist(c);
				entityManager.getTransaction().commit();
			}
		}
		catch (Exception e) {
			System.out.println(e);
		};
	}
	

	public static Country getCountryByID (int countryID) {
		Country c = new Country();
		try {
			c = entityManager.find(Country.class,countryID);
		}
		catch (Exception e) {
			System.out.println(e);
			
		};
		return c;
		
	}

	public static void deleteCountryByID (int id) {
		try {
			Country c =getCountryByID(id);
			entityManager.getTransaction().begin();
			entityManager.remove(c);
			entityManager.getTransaction().commit();
		}
		catch (Exception e) {
			System.out.println(e);
		};
	}

	public static void updateCountryPopulationByID (int id, int population) {
		try {
			Country c =getCountryByID(id);
			entityManager.getTransaction().begin();
			c.setPopulation(population);
			entityManager.merge(c);
			entityManager.getTransaction().commit();
		}
		catch (Exception e) {
			System.out.println(e);
		};

	}
}
