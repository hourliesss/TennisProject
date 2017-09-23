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

	public Tournament(String name,Calendar begin){
		this.begin = begin;
		this.end = begin;
		this.name = name;
		this.surface = null;
		this.stageNumber = 0;
		this.participants= new ArrayList<Player>();
		this.category = null;
        }
	public String getName(){
		return this.name;
	} 	
        
        public void setBegin(Calendar cal){
            this.begin = cal;
        }
        
        public Calendar getBegin(){
            return this.begin;
        }
        
        public ArrayList<Player> getParticipants(){
            return this.participants;
        }
        
        public Calendar getEnd(){
            return this.end;
        }
        public void setEnd(Calendar cal){
            this.end = cal;
        }
        
        public void stageNumber(int i){
            this.stageNumber = i;
        }
        
        public void setParticipants(ArrayList<Player> participants){
            this.participants = participants;
        }
        
        
        public void addPlayer(ArrayList<Player> players,String name){
            boolean bool = false;
            System.out.println(this.participants.size());
            if ((name != null) && (this.participants.size()>0)){
                for(Player p : this.participants){
                    if ((p.getName()).equals(name)){
                        bool = true;
                    }
                }
                   
            if (bool == false){
                Player newPlayer = new Player("Cassius");
                for(Player p : players){
                    if ((p.getName()).equals(name)){
                        newPlayer = p;
                    }
            }
                this.participants.add(newPlayer);
                 
            }
        }
    
    }
        
        public Player getParticipantByName(String name){
            Player newPlayer = null;
                for(Player p : this.participants){
                    if ((p.getName()).equals(name)){
                        newPlayer = p;
                    }
            }
                return newPlayer;
        }
        
        
	public String toString(){
		return this.name + " qui a debuté le " + this.begin + " et a fini le " + this.end +
			   ". C'est un " + this.category + ", il y avait " + this.participants.size() + " participants";
	}
}