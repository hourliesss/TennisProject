public class State{

	private int health;
	private double ranking;
	private Date date;

	public State(int health,double ranking, Date date){
		this.health = health;
		this.ranking = ranking;
		this.date = date;
	}

	public int getHealth(){
		return this.health;
	}

	public double getRanking(){
		return this.ranking;
	}

	public Date getDate(){
		return this.date;
	}
}