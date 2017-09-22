import java.util.ArrayList;
import java.util.Calendar;

public class Tournament{

	private Calendar begin;
	private Calendar end;
	private String name;
    private Surface surface;
	private int stageNumber;
	private ArrayList<Player> participants;
	private Category category;

	public Tournament(Calendar begin, Calendar end, String name, Integer stageNumber, ArrayList<Player> participants){
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.surface = null;
		this.stageNumber = stageNumber;
		this.participants= participants;
		this.category = null;
        }
	public String getName(){
		return this.name;
	} 	

	public String toString(){
		return this.name + " qui a debuté le " + this.begin + " et a fini le " + this.end + "s'est joué sur du " + this.surface +
			   ". C'est un " + this.category + ", il y avait " + this.participants.size() + " participants";
	}
}