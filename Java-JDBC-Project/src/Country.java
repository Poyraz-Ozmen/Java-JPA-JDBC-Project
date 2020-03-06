import java.util.concurrent.atomic.AtomicInteger;


public class Country {
	private int id;
	private String name;
	private String continent;
	private String capital;
	private int population;
	
	private static final AtomicInteger idCount = new AtomicInteger(0);

	public Country() {}
	
	public Country(String name, String continent, String capital, int population) {
		id = idCount.incrementAndGet();
		this.name = name;
		this.continent = continent;
		this.capital = capital;
		this.population = population;
	}

	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getContinent() {
		return continent;
	}
	public String getCapital() {
		return capital;
	}
	public int getPopulation() {
		return population;
	}
    public void setId(int id) {
        this.id = id;
    }
	
    public void setName(String name) {
        this.name = name;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }
    public void setPopulation(int population) {
        this.population = population;
    }

}