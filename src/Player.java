import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                PlayerState initialState = new PlayerState(50000, 100, atpRanking);
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
        
        /**
         * number of days elapsed between two calendars
         **/
        public int daysBetween(Calendar c1, Calendar c2) {
            Date date1 = c1.getTime();
            Date date2 = c2.getTime();
            double timestampInMilliS = date1.getTime() - date2.getTime();
            return Math.abs((int)timestampInMilliS/86400000);
        }
     
        public void setHealth(){
            Calendar today = Calendar.getInstance();
            //int atpRanking;
            if (!this.matches.isEmpty()) {
                Calendar lastDate = this.matches.get(this.matches.size() - 1).getDate();
                int currentHealth = Math.min(daysBetween(today, lastDate)*10 + getHealth(), 100);
                for (TennisMatch m : this.matches) {
                    currentHealth -= m.getGamesNb()/2;
                }
                this.getState().setHealth(currentHealth);
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
