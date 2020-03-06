import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CONTINENT")
	private String continent;
	
	@Column(name = "CAPITAL")
	private String capital;
	
	@Column(name = "POPULATION")
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