import java.util.LinkedList;
import java.util.Calendar;

public class Match {

		private Player player1;
		private Player player2;
		private LinkedList<Integer> score1;
		private LinkedList<Integer> score2;
		private Tournament tournament;
		private Calendar date;
		private int stage;

		public Match(Player player1, Player player2, LinkedList<Integer> score1, LinkedList<Integer> score2, Tournament tournament, Calendar date, int stage){
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
                public int getGap() {
                    int sum1 = this.score1.stream().mapToInt(Integer::intValue).sum();
                    int sum2 = this.score2.stream().mapToInt(Integer::intValue).sum();
                    return sum1 - sum2;
                }

}