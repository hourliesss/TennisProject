import java.util.LinkedList;
import java.util.Calendar;

public class TennisMatch {

		private final Player player1;
		private final Player player2;
		private final LinkedList<Integer> score1;
		private final LinkedList<Integer> score2;
		private final Tournament tournament;
		private final Calendar date;
		private final int stage;

		public TennisMatch(Player player1, Player player2, LinkedList<Integer> score1, LinkedList<Integer> score2, Tournament tournament, Calendar date, int stage){
			this.player1 = player1;
			this.player2 = player2;
			this.score1 = score1;
			this.score2 = score2;
			this.tournament = tournament;
			this.date = date;
			this.stage = stage;
		}

                public Player getP1(){
                    return this.player1;
                }
                
                public Player getP2(){
                    return this.player2;
                }
                
                @Override
		public String toString(){
			return "Ce match qui s'est déroulé le " + this.date.getTime() + " à " 
                                + this.tournament.getName() + " lors du " + this.stage + 
                                " tour, " + " a opposé " + this.player1.getName() + 
                                " à " + this.player2.getName();
		}
                
                public Calendar getDate() {
                    return this.date;
                }
                
                public Tournament getTournament(){
                    return this.tournament;
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

}