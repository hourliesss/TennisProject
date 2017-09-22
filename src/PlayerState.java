public class PlayerState {

		private double ranking;
                private int health;

		public PlayerState(double ranking, int health){
			this.ranking = ranking;
                        this.health = health;
		}

                public double getRanking(){
                    return this.ranking;
                }
                        
                public int getHealth() {
                    return this.health;
                }
 
}