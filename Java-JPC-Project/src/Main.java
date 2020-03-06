import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String filename = "world.txt";
		ArrayList<Country> listCountry = PoyrazJPAManager.readFromFile(filename);
		//System.out.println(listCountry.get(1).getName()); // passed
		PoyrazJPAManager.writeIntoTable(listCountry); // passed
		
		Country temp;
		temp = PoyrazJPAManager.getCountryByID(1); // passed
		System.out.println("Country object with ID:1 is :"+" "+temp);
		//System.out.println(temp);
		System.out.println(temp.getID()+ " " +temp.getName()+ " " +temp.getCapital()+ " " +temp.getContinent()+ " " +temp.getPopulation());
		
		PoyrazJPAManager.deleteCountryByID(3); // passed
		System.out.println("Deleted country where id = 3");
		
		
		PoyrazJPAManager.updateCountryPopulationByID(4,100); // passed
		System.out.println("Updated id=4 population to 100");
	}

}
