
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author simsim
 */
public class SimulationData {
    
    private final ArrayList<Tournament> tournaments;
    private final ArrayList<Player> players;
    private final ArrayList<TennisMatch> matches;
    
    public SimulationData(){
        this.tournaments = new ArrayList<>();
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }
    
    public ArrayList<Tournament> getTournaments(){
        return this.tournaments;
    }
    
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
    public ArrayList<TennisMatch> getMatches(){
        return this.matches;
    }
    
    public Player getPlayerByName(String name){
            return this.players.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        }
    
    public void addTournament(Tournament tournament){
        this.tournaments.add(tournament);
    }
    
    public void addPlayer(String name, int atpRanking){
        boolean bool = false;
        if (!name.equals("")){
            for(Player p : players){
                if ((p.getName()).equals(name)){
                    bool = true;
                }
            }
            if (!bool)
              this.players.add(new Player(name,atpRanking));
        }
    }
    
    public void addMatch(TennisMatch match){
        this.matches.add(match);
    }
    
}
