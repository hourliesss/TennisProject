import java.util.ArrayList;
import java.util.Calendar;

public class Tournament{

	private Calendar begin;
	private Calendar end;
	private final String name;
        private final Surface surface;
	private int stageNumber;
	private ArrayList<Player> participants;
	private final Category category;

	public Tournament(String name,Calendar begin,Category category){
		this.begin = begin;
		this.end = begin;
		this.name = name;
		this.surface = null;
		this.stageNumber = 0;
		this.participants= new ArrayList<>();
		this.category = category;
        }
	public String getName(){
		return this.name;
	} 
        
        public Category getCategory(){
		return this.category;
	} 	
        
        public void setBegin(Calendar cal){
            this.begin = cal;
        }
        
        public void setStageNumber(int i){
            this.stageNumber = i;
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
            if (!name.equals("")){
                if (this.participants.size()>0){
                    for(Player p : this.participants){
                        if ((p.getName()).equals(name)){
                            bool = true;
                        }
                    }
                }
                if (!bool){
                    Player newPlayer = players.stream().filter(p -> p.getName()
                            .equals(name)).findFirst().orElse(new Player("Cassius",1));
                    this.participants.add(newPlayer);
                }
            }
    }
        
        public Player getParticipantByName(String name){
            return this.participants.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        }
        
        @Override
	public String toString(){
		return this.name + " qui a debuté le " + this.begin.getTime() + " et a fini le " + this.end.getTime() +
			   ". C'est un " + this.category + ", il y avait " + this.participants.size() + " participants";
	}
}