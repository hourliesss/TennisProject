import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Player {

	private String firstname;
	private String lastname;
	private Calendar birthDate;
	private ArrayList<Match> matchs;
	private TreeMap<Calendar,PlayerState> stateMap;

	public Player(String firstname, String lastname, Calendar birthDate){
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthDate = birthDate;
		this.matchs = new ArrayList<>();
                PlayerState state = new PlayerState(0, 100);
		this.stateMap = new TreeMap<>();
                this.stateMap.put(new GregorianCalendar(1970,1,1), state);
	}

        public void updateState(Calendar date, PlayerState state) {
            this.stateMap.put(date, state);
        }
	
	public String toString(){
                Entry<Calendar, PlayerState> currentEntry = this.stateMap.lastEntry();
                double currentRanking = currentEntry.getValue().getRanking();
                int currentHealth = currentEntry.getValue().getHealth();
		return "Bonjour je m'appelle " + this.firstname + " " + this.lastname + 
                        ". Je suis né le " + this.birthDate +". Je suis à " + currentHealth
                        + "% de ma condition physique et mon ranking est : " + currentRanking;
	}

	public String getFirstname(){
		return this.firstname;
	}

	public String getLastname(){
		return this.lastname;
	}

	

}
