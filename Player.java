import java.util.Date;
import java.util.Map;
import java.util.ArrayList;

public class Player{

	private String firstname;
	private String lastname;
	private Date birthDate;
	private ArrayList<Match> matchs;
	private tabledehachage date classement forme;

	public Player(String firstname, String lastname, Date birthDate){
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		value = new ArrayList<Match>();
		forme a 0;

	}

	
	public String toString(){
		return "Bonjour je m'appelle " + this.firstname + " " + this.lastname + " et je suis né le " + this.birthDate;
	}

	public String getFirstname(){
		return this.firstname;
	}

	public String getLastname(){
		return this.lastname;
	}

	

}
