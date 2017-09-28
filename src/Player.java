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
                /* Classic initialization */
                 PlayerState initialState = new PlayerState(50000, 100, atpRanking);
                /* Initializtion related to ranking */
                 //   PlayerState initialState = new PlayerState(75000- Math.min(atpRanking,200)*250,100,atpRanking);
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
                return lol.getName().equals(this.name);
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
        public static double f(double x) {
                    return 1000*Math.exp(x/(60000));
                }
        
        public static double g(int wonGames, int lostGames,int setNumbers, double x) {
            return 1000*Math.atan(2*(wonGames- lostGames)/setNumbers-4) + h(x);
        }

        private static double h(double x) {
            if (x >= 0)
                return 1200*Math.log(x/20000 + 1);
            else
                return -1200*Math.log(-x/20000 + 1);
        }

        private double rankingFuncWin(int wonGames, int lostGames, int setNumbers, double diffWeights, int coeffF, int coeffG) {
            if (setNumbers % 2 == 1){ //If a player abandoned, the result does not matter
                return coeffG*g(wonGames, lostGames, setNumbers, diffWeights);
            }
            else{
                return coeffF*f(diffWeights) + coeffG*g(wonGames, lostGames, setNumbers, diffWeights);
            }

        }

        private double rankingFuncLoss(int wonGames, int lostGames, int setNumbers, double diffWeights, int coeffF, int coeffG) {
            if (setNumbers % 2 == 1){ //If a player abandoned, the result does not matter
                return - coeffG*g(lostGames, wonGames, setNumbers, -diffWeights);
            }
            else{
                return -coeffF*f(-diffWeights) - coeffG*g(lostGames, wonGames, setNumbers, -diffWeights);
            }

        }
                
        public static double healthFunction(int health){
                return 0.85+0.10*Math.atan(0.03*(health-50));
        }
        
         public void updateRanking(Calendar matchDate, int atpRanking, int coeffF, int coeffG) {
             
             if (!this.matches.isEmpty()){
                    
                    TennisMatch lastMatch = this.matches.get(this.matches.size()-1);
                    int setNb = lastMatch.getSetNb();
                    int gamesNb = lastMatch.getGamesNb();
                    int health1 = Math.min(this.getHealth()-gamesNb + Player.daysBetween(matchDate,lastMatch.getDate())*10,100);
                    int health2 = Math.min(lastMatch.getP2().getHealth()-gamesNb + Player.daysBetween(matchDate,lastMatch.getDate())*10,100);
                    double ranking1 = getRanking();
                    double ranking2 = lastMatch.getP2().getRanking();
                    double diffWeights = ranking2*healthFunction(health2) - ranking1*healthFunction(health1);
                    double newRanking1;
                    double newRanking2;
                    if (setNb == 0){ //No match, one player abandoned before start
                        PlayerState p1 = new PlayerState(ranking1, getHealth(), atpRanking);
                        this.stateMap.put(matchDate,p1);
                    }
                    else{
                        if (this.equals(lastMatch.getWinner())) {
                            newRanking1 = ranking1 + rankingFuncWin(lastMatch.getWonGamesPlayer1(), lastMatch.getWonGamesPlayer2(), setNb, diffWeights, coeffF, coeffG);
                          }   
                        else {
                            newRanking1 = ranking1 + rankingFuncLoss(lastMatch.getWonGamesPlayer1(), lastMatch.getWonGamesPlayer2(), setNb, diffWeights, coeffF, coeffG);
                          }
                         PlayerState p1 = new PlayerState(newRanking1,health1, atpRanking);
                         this.stateMap.put(matchDate,p1);
                    }
                    
                   
                }
             else{
                 PlayerState initialState = new PlayerState(50000, 100, atpRanking);
		this.stateMap = new TreeMap<>();
                this.stateMap.put(matchDate, initialState);
             }
         }
        
        
        
	

}
