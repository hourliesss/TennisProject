import java.util.ArrayList;
import java.io.File;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author simsim
 */
public class Ringo {
    
    public static void testRatio(){
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        int badResult=0; int goodResult=0;
        int bestF = 0; int bestG = 0; 
        int bestGoodResult = 0;
        for (int F = 1; F < 10 ; F++){
            for (int G = 1; G < 10;G++){
                badResult = 0; goodResult = 0;
                SimulationData simulationData = dataReader.getSimulationData(F,G);
                for(Player p : simulationData.getPlayers()){
                    
                    if (p.getMatches().size() > 30 ){ //Wait for ranking to set a correct value
                        for (int i = 30;i<p.getMatches().size();i++){
                            if (p.getMatches().get(i).getP2().getStateMap().size() >= 30){
                                int pos = 0; //Does the oppenent already play 10 matches?
                                for (Calendar key : p.getMatches().get(i).getP2().getStateMap().keySet()) {

                                    if(key.before(p.getMatches().get(i).getDate())){
                                        pos++;
                                        
                                    }
                                }

                                if (pos>30){
                                    if ( (p.getMatches().get(i).getWinner().equals(p)) && (p.getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(p.getMatches().get(i).getDate()).getHealth()) >  p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getHealth())) || (!p.getMatches().get(i).getWinner().equals(p)) && (p.getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(p.getMatches().get(i).getDate()).getHealth()) <  p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getHealth())) ) {
                                       goodResult += 1;
                                   }
                                   else{
                                       badResult += 1;
                                   }
                                }
                            }
                    }
                    }
                }
                
                if (bestGoodResult<goodResult){
                    bestF = F;
                    bestG = G;
                    bestGoodResult = goodResult;
                }
                System.out.println("Avec F = " + F + " et G = " + G + " On obtient " + goodResult + " bons résultats et " + badResult + " mauvais résultats");
            }
            
        }
            System.out.println("Meilleur combinaison : (F,G) = (" + bestF + "," + bestG + ") = " + bestGoodResult + " sur " + (goodResult + badResult) + " matchs");
    }
    
    public static void simpleTest(){
        System.out.println("Let's play Tennis with Cassius and Simon ! ");
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData(3,2);
                           
        /*
                            *** Test One Player ranking's evolution
                                                               */
       if (true){
           Iterator it = simulationData.getPlayerByName("Rafael Nadal").getStateMap().entrySet().iterator();
           while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getValue().toString());
                it.remove(); // avoids a ConcurrentModificationException
        }
       }
        
               
        /*
        
                        *** All Players last ranking
                                                               */
        if (false){
        for (Player p : simulationData.getPlayers()){
            if (p.getMatches().size() >= 10)
                System.out.println(p.getName() + ", ranking :  " +p.getRanking());
        }
       
        }
        
                        /*** MIXED ***/
                                
            
        if (false){
            int compteur = 0;
            for (Player p : simulationData.getPlayers()){
                if (p.getMatches().size() >= 10){
                    compteur++;
                    Iterator it = p.getStateMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        System.out.println(p.getName() + ", ranking :  " + pair.getValue().toString());
                        it.remove(); // avoids a ConcurrentModificationException
            }
                
            }
        }
            System.out.println("Il y a " + compteur + " joueurs a plus de 10 matchs sur " + simulationData.getPlayers().size());
       
        }
        /**                 TEST FONCTIONS F,G,H   **/
        
        
    }
    
    
    public static void main(String[] args){
        
        // simpleTest();
        testRatio();
        
    }
  
}
