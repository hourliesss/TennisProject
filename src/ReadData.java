import java.io.File;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author simsim
 */

public class ReadData {
    
    private File f;
    
    public ReadData(File f){
        this.f = f;
    }
    
    public SimulationData getSimulationData(){
        
        SimulationData simulationData = new SimulationData();
        
        
     
      try {
         
         InputStream ips = new FileInputStream(f);
         InputStreamReader ipsr = new InputStreamReader(ips);
         BufferedReader br = new BufferedReader(ipsr);
         
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         String line = null;
         Calendar tournamentBegin= Calendar.getInstance();
	 Calendar tournamentEnd = Calendar.getInstance();
         Calendar cal = Calendar.getInstance();
         String tournamentsName = "Cassius and Simon Tournament(coming soon)";
	 int stageNumber = 0;
         String round = null;
        boolean bool;
         while ((line=br.readLine())!=null){
                String values[] = line.split("\t");
                if (!tournamentsName.equals(values[0])){
                    stageNumber = 1;
                    round = values[2];
                    cal.setTime(df.parse(values[1]));
                    simulationData.addTournament(new Tournament(values[0],cal));
                    simulationData.addPlayer(values[3]); //add first player to the list pf players if he is not already in
                    if (values.length > 5){
                        simulationData.addPlayer(values[5]);   //add oppenents if not already in the list AND if exists
                        simulationData.getTournaments().get(simulationData.getTournaments().size() -1).addPlayer(simulationData.getPlayers(),values[5]);
                    }
                    System.out.println(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getName());
                    simulationData.getTournaments().get(simulationData.getTournaments().size() -1).addPlayer(simulationData.getPlayers(),values[3]);
                    
                    if (values.length > 5){
                        simulationData.addMatch(new Match(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getParticipants().get(simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().size()-1),simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().get(simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().size()-2),addScore(values,1),addScore(values,2),simulationData.getTournaments().get(simulationData.getTournaments().size()-1),cal,stageNumber));
                    }
                    
                }
                else{
                    cal.setTime(df.parse(values[1]));
                    if (cal.before(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getBegin())){
                        simulationData.getTournaments().get(simulationData.getTournaments().size() -1).setBegin(cal);
                    }
                    if (cal.after(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getEnd())){
                       simulationData.getTournaments().get(simulationData.getTournaments().size() -1).setEnd(cal);
                    }
                    if (!values[2].equals(round)){
                        round = values[2];
                        stageNumber++;
                    }
                    if (stageNumber == 1){
                        simulationData.addPlayer(values[3]); //add first player to the list pf players if he is not already in
                        simulationData.addPlayer(values[5]);   //add oppenents if not already in the list AND if exists
                        simulationData.getTournaments().get(simulationData.getTournaments().size() -1).addPlayer(simulationData.getPlayers(),values[3]);
                        simulationData.getTournaments().get(simulationData.getTournaments().size() -1).addPlayer(simulationData.getPlayers(),values[5]);
                         if (values[5] != null){
                            simulationData.addMatch(new Match(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getParticipants().get(simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().size()-1),simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().get(simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipants().size()-2),addScore(values,1),addScore(values,2),simulationData.getTournaments().get(simulationData.getTournaments().size()-1),cal,stageNumber));
                    }
                    }
                    else{
                        simulationData.addMatch(new Match(simulationData.getTournaments().get(simulationData.getTournaments().size() -1).getParticipantByName(values[3]),simulationData.getTournaments().get(simulationData.getTournaments().size()-1).getParticipantByName(values[5]),addScore(values,1),addScore(values,2),simulationData.getTournaments().get(simulationData.getTournaments().size()-1),cal,stageNumber));
                    }
                    }
                }
                    
       
                        
        
        br.close(); 
        }
        catch (FileNotFoundException e){
                System.out.println(e.toString());
        }
        catch (IOException e){
                System.out.println(e.toString());
        }
        catch (Exception e){
                System.out.println(e.toString());
        }
      
        




        return simulationData;
        
    }
    
    
    
    public static ArrayList<Player> addParticipants(ArrayList<Player> participants,ArrayList<Player> players, String name){
        if (name != null){
            boolean bool = false;
            for(Player p : players){
                if ((p.getName()).equals(name)){
                    bool = true;
                }
            }
            if (bool == false){
                players.add(new Player(name));
            }
        }
    
        return players;
    }
 
    public static LinkedList<Integer> addScore(String values[],int i){
        LinkedList<Integer> score = new LinkedList<Integer>();
        if (i == 1){
            for (int j=9;j<15;j++){
                if ( (values[j] != null) && (!values[j].equals("A")) ){
                    score.add(Integer.parseInt(values[j]));
                }
                if (values[j].equals("A")){
                    score.add(0);
                }
            }
            
        }
        
        if (i == 2){
            for (int j=15;j<21;j++){
                if ( (values[j] != null) && (!values[j].equals("A")) ){
                    score.add(Integer.parseInt(values[j]));
                }
                if (values[j].equals("A")){
                    score.add(0);
                }
            }
            
        }
    
    
    return score;
    
    
}
    
    
    
}
