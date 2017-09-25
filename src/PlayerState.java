public class PlayerState {

		private double ranking;
                private int health;
                private int atpRanking;

		public PlayerState(double ranking, int health,int atpRanking){
			this.ranking = ranking;
                        this.health = health;
                        this.atpRanking = atpRanking;
		}

                public double getRanking(){
                    return this.ranking;
                }
                        
                public int getHealth() {
                    return this.health;
                }
                
                public int getAtpRanking() {
                    return this.atpRanking;
                }
 
 
}