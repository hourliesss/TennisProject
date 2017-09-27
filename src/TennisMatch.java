import java.util.LinkedList;
import java.util.Calendar;

public class TennisMatch {

		private Player player1;
		private Player player2;
		private LinkedList<Integer> score1;
		private LinkedList<Integer> score2;
		private Tournament tournament;
		private Calendar date;
		private int stage;

		public TennisMatch(Player player1, Player player2, LinkedList<Integer> score1, LinkedList<Integer> score2, Tournament tournament, Calendar date, int stage){
			this.player1 = player1;
			this.player2 = player2;
			this.score1 = score1;
			this.score2 = score2;
			this.tournament = tournament;
			this.date = date;
			this.stage = stage;
		}

		public String toString(){
			return "Ce match qui s'est déroulé le " + this.date.getTime() + " à " 
                                + this.tournament.getName() + " lors du " + this.stage + 
                                " tour, " + " a opposé " + this.player1.getName() + 
                                " à " + this.player2.getName();
		}
                
                public Calendar getDate() {
                    return this.date;
                }
                
                public Player getWinner() {
                    if (this.score1.size() > this.score2.size()){ //Player1 abandoned
                        return this.player2;
                    }
                    if (this.score2.size() > this.score1.size()){ //Player2 abandoned
                        return this.player1;
                    }
                    int count1 = 0;
                    int count2 = 0;
                    for (int i=0; i<this.score1.size(); i++) {
                        if (this.score1.get(i) > this.score2.get(i))
                            count1++;
                        else
                            count2++;
                    }
                    if (count1 > count2)
                        return this.player1;
                    else
                        return this.player2;
                }
                
                /**
                 *   Reveals the gap between the two players' scores
                 *   formula : sum(scores(P1)) - sum(scores(P2))
                 **/
                public int getGapScore() {
                    int sum1 = this.score1.stream().mapToInt(Integer::intValue).sum();
                    int sum2 = this.score2.stream().mapToInt(Integer::intValue).sum();
                    return sum1 - sum2;
                }
                
                public int getWonSetsP1() {
                    int count = 0;
                    for (int i=0; i<this.score1.size(); i++) {
                        if (this.score1.get(i) > this.score2.get(i))
                            count++;
                    }
                    return count;
                }
                
                public int getWonSetsP2() {
                    return this.score1.size() - getWonSetsP1();
                }
                
                public int getGapHealth() {
                    int health1 = this.player1.getHealth();
                    int health2 = this.player2.getHealth();
                    return health1 - health2;
                }
                
                public double getGapRanking() {
                    double ranking1 = this.player1.getRanking();
                    double ranking2 = this.player2.getRanking();
                    return ranking1 - ranking2;
                }
                
                public static double f(double x) {
                    return 2000*Math.exp(x/(60000));
                }
                public static double g(int wonGames, int lostGames,int setNumbers, double x) {
                    return 4000*Math.atan(2*(wonGames- lostGames)/setNumbers-4)/3 + h(x);
                }
                
                private static double h(double x) {
                    if (x >= 0)
                        return 1200*Math.log(x/20000 + 1);
                    else
                        return -1200*Math.log(-x/20000 + 1);
                }
                
                private double rankingFuncWin(int wonGames, int lostGames, int setNumbers, double diffWeights) {
                    if (this.score1.size() != this.score2.size()){ //If a player abandoned, the result does not matter
                        return g(wonGames, lostGames, setNumbers, diffWeights);
                    }
                    else{
                        return f(diffWeights) + g(wonGames, lostGames, setNumbers, diffWeights);
                    }
                    
                }
                
                private double rankingFuncLoss(int wonGames, int lostGames, int setNumbers, double diffWeights) {
                    if (this.score1.size() != this.score2.size()){ //If a player abandoned, the result does not matter
                        return - g(lostGames, wonGames, setNumbers, -diffWeights);
                    }
                    else{
                        return -f(-diffWeights) - g(lostGames, wonGames, setNumbers, -diffWeights);
                    }
                    
                }
                
                public int getGamesNb(){
                    return this.score1.stream().mapToInt(Integer::intValue).sum() + 
                            this.score2.stream().mapToInt(Integer::intValue).sum();
                }
                
                public int getSetNb(){
                    return 2*Math.min(this.score1.size(),this.score2.size());
                }
                
                 public int getWonGamesPlayer1(){
                    return this.score1.stream().mapToInt(Integer::intValue).sum();
                }
                 
                 public int getWonGamesPlayer2(){
                    return this.score2.stream().mapToInt(Integer::intValue).sum();
                }
               
       
                public void updateRanking(int atpRanking1, int atpRanking2) {
                    int setNb = getSetNb();
                    int gamesNb = getGamesNb();
                    int health1 = Math.min(this.player1.getHealth()-gamesNb + Player.daysBetween(this.date,this.player1.getStateMap().lastKey())*10,100);
                    int health2 = Math.min(this.player2.getHealth()-gamesNb + Player.daysBetween(this.date,this.player2.getStateMap().lastKey())*10,100);
                    double ranking1 = this.player1.getRanking();
                    double ranking2 = this.player2.getRanking();
                    //TO DO Fonction for impact of Health not Linear
                    //double diffWeights = ranking2*((double)health2/100) - ranking1*(double)(health1)/100;
                    double diffWeights = ranking2 - ranking1;
                    double newRanking1;
                    double newRanking2;
                    if (setNb == 0){ //No match, one player abandoned before start
                        PlayerState p1 = new PlayerState(ranking1, this.player1.getHealth(), atpRanking1);
                        PlayerState p2 = new PlayerState(ranking2, this.player2.getHealth(), atpRanking2);
                        this.player1.getStateMap().put(this.date,p1);
                        this.player2.getStateMap().put(this.date,p2);
                    }
                    else{
                        if (this.player1.equals(getWinner())) {
                            newRanking1 = ranking1 + rankingFuncWin(getWonGamesPlayer1(), getWonGamesPlayer2(), setNb, diffWeights);
                            newRanking2 = ranking2 + rankingFuncLoss(getWonGamesPlayer2(), getWonGamesPlayer1(), setNb, -diffWeights);
                        }   
                        else {
                            newRanking1 = ranking1 + rankingFuncLoss(getWonGamesPlayer1(), getWonGamesPlayer2(), setNb, diffWeights);
                            newRanking2 = ranking2 + rankingFuncWin(getWonGamesPlayer2(), getWonGamesPlayer1(), setNb, -diffWeights);
                        }
                         PlayerState p1 = new PlayerState(newRanking1,health1, atpRanking1);
                         PlayerState p2 = new PlayerState(newRanking2,health2, atpRanking2);
                         this.player1.getStateMap().put(this.date,p1);
                         this.player2.getStateMap().put(this.date,p2);
                    }
                    
                   
                }
                
                public void updateStates() {
                    
                    /*int newHealth1 = health1 - gamesNb;
                    int newHealth2 = health2 - gamesNb;*/
                    
                    
                    
                    
                    
                   //this.player1.updateState(this.date, new PlayerState(newRanking1, newHealth1));
                   //this.player2.updateState(this.date, new PlayerState(newRanking2, newHealth2));
                }

}