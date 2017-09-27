
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
    
    private ArrayList<Tournament> tournaments;
    private ArrayList<Player> players;
    private ArrayList<Match> matches;
    
    public SimulationData(){
        this.tournaments = new ArrayList<Tournament>();
        this.players = new ArrayList<Player>();
        this.matches = new ArrayList<Match>();
    }
    
    public ArrayList<Tournament> getTournaments(){
        return this.tournaments;
    }
    
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    
    public ArrayList<Match> getMatches(){
        return this.matches;
    }
    
    public Player getPlayerByName(String name){
            Player newPlayer = null;
                for(Player p : this.players){
                    if ((p.getName()).equals(name)){
                        newPlayer = p;
                    }
            }
                return newPlayer;
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
            
            if (bool == false){
                this.players.add(new Player(name,atpRanking));
                
            }
        }
    
        
    }
    
    public void addMatch(Match match){
        this.matches.add(match);
    }
    
}
