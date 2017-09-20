import java.util.Date;
import java.util.ArrayList;

public class Tournament{

	private Date begin;
	private Date end;
	private String name;
	private Surface surface;
	private Integer stageNumber;
	private ArrayList<Player> participants;
	private Category category;

	public Tournament(Date begin, Date end, String name, Surface surface, Integer stageNumber, ArrayList<Player> participants, Category category){
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.surface = surface;
		this.stageNumber = stageNumber;
		this.participants= participants;
		this.category = category;
	}
	
	public String getName(){
		return this.name;
	} 	

	public String toString(){
		return this.name + " qui a debuté le " + this.begin + " et a fini le " + this.end + "s'est joué sur du " + this.surface +
			   ". C'est un " + this.category + ", il y avait " + this.participants + " participants";
	}
}