import java.util.LinkedList;
import java.util.Date;

public class Match {

		private Player player1;
		private Player player2;
		private LinkedList<Integer> score1;
		private LinkedList<Integer> score2;
		private Tournament tournament;
		private Date date;
		private Integer stage;

		public Match(Player player1, Player player2, LinkedList<Integer> score1, LinkedList<Integer> score2, Tournament tournament, Date date, Integer stage){
			this.player1 = player1;
			this.player2 = player2;
			this.score1 = score1;
			this.score2 = score2;
			this.tournament = tournament;
			this.date = date;
			this.stage = stage;
		}

		public String toString(){
			return "Ce match qui s'est déroule le " + this.date + " à " + this.tournament.getName() + " lors du " + this.stage + " tour, " + " a opposé " + this.player1.getFirstname() + " à " + this.player2.getFirstname() + " lors du " + this.stage + "e tour";
		}

}