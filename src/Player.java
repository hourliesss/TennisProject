import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Player {

	private String name;
	private Calendar birthDate;
	private ArrayList<TennisMatch> matches;
	private TreeMap<Calendar,PlayerState> stateMap;

	public Player(String name, int atpRanking){
		this.name = name;
		this.birthDate = null;
		this.matches = new ArrayList<>();
                PlayerState initialState = new PlayerState(50000, 100,atpRanking);
		this.stateMap = new TreeMap<>();
                this.stateMap.put(new GregorianCalendar(2010,1,1), initialState);
	}

        
        public void updateState(Calendar date, PlayerState state) {
            this.stateMap.put(date, state);
        }
        
        public PlayerState getState() {
            return this.stateMap.lastEntry().getValue();
        }
        
        public ArrayList<TennisMatch> getMatches() {
            return this.matches;
        }
        
        public void addMatch(TennisMatch match){
            this.matches.add(match);
        }
        
        public double getRanking() {
            return this.getState().getRanking();
        }
        
        public int getHealth() {
            return this.getState().getHealth();
        }
	
        public void setHealth(){
            Calendar cal = new GregorianCalendar(1970,1,1);
            Calendar today = Calendar.getInstance();
            int tired = 0;
            int atpRanking;
            for (int i = 0;i< this.matches.size();i++){
                tired = this.matches.get(i).getGamesNb(); 
            
            }
        }
        
       
        
	public String toString(){
                Entry<Calendar, PlayerState> currentEntry = this.stateMap.lastEntry();
                double currentRanking = currentEntry.getValue().getRanking();
                int currentHealth = currentEntry.getValue().getHealth();
		return "Bonjour je m'appelle " + this.name +  
                        ". Je suis né le " + this.birthDate +". Je suis à " + currentHealth
                        + "% de ma condition physique et mon ranking est : " + currentRanking;
	}

	public String getName(){
		return this.name;
	}


	

}
