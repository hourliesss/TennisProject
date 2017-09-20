import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

public class Player{

	private String firstname;
	private String lastname;
	private Date birthDate;
	private ArrayList<Match> matchs;
	private HashMap<Date,State> state;

	public Player(String firstname, String lastname, Date birthDate){
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.matchs = new ArrayList<Match>();
		this.state = new ArrayList<State>();

	}

	
	public String toString(){
		return "Bonjour je m'appelle " + this.firstname + " " + this.lastname + ". Je suis né le " + this.birthDate +". Je suis à " + this.health + "% de ma condition physique et mon ranking est : " + this.ranking;
	}

	public String getFirstname(){
		return this.firstname;
	}

	public String getLastname(){
		return this.lastname;
	}

	

}
