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
                
                public double rankingFunc(double x) {
                    return x;
                }
                
                public int getGamesNb(){
                    return this.score1.stream().mapToInt(Integer::intValue).sum() + 
                            this.score2.stream().mapToInt(Integer::intValue).sum();
                }
                
                
                public void updateStates() {
                    int health1 = this.player1.getHealth();
                    int health2 = this.player2.getHealth();
                    int gamesNb = getGamesNb();
                    int newHealth1 = health1 - gamesNb;
                    int newHealth2 = health2 - gamesNb;
                    
                    int gapScore = getGapScore();
                    int gapHealth = getGapHealth();
                    double gapRanking = getGapRanking();
                    
                    double ranking1 = this.player1.getRanking();
                    double ranking2 = this.player2.getRanking();
                    
                    double coeff = gapScore*gapHealth/100;
                    
                    double newRanking1 = ranking1 + coeff*rankingFunc(gapRanking);
                    double newRanking2 = ranking2 + coeff*rankingFunc(-gapRanking);
                    
                  //  this.player1.updateState(this.date, new PlayerState(newRanking1, newHealth1));
                   // this.player2.updateState(this.date, new PlayerState(newRanking2, newHealth2));
                }

}