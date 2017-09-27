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

        public Calendar getBirthDate() {
            return this.birthDate;
        }
        
        public boolean equals(Object o) {
            boolean inst = o instanceof Player;
            if (inst) {
                Player lol = (Player) o;
                return lol.getName().equals(this.name) && lol.getBirthDate().equals(this.birthDate);
            }
             else
                return false;
        }
        
        public void updateState(Calendar date, PlayerState state) {
            this.stateMap.put(date, state);
        }
        
        public TreeMap<Calendar,PlayerState> getStateMap() {
            return this.stateMap;
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
        public static int daysBetween(Calendar c1, Calendar c2) {
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
                int health = daysBetween(today, lastDate)*10;
                for (TennisMatch m : this.matches) {
                    health -= m.getGamesNb()/2;
                }
                if (health < 0)
                    health = 0;
                this.getState().setHealth(Math.min(health, 100));
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
