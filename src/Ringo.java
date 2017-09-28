import java.util.ArrayList;
import java.io.File;
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
                for(TennisMatch m : simulationData.getMatches()){
                  // System.out.println(goodResult+badResult);
                    if ((m.getP1().getMatches().size()  > 15) && (m.getP2().getMatches().size()  > 15) ){
                    //     System.out.println(m.getP1().getName() + m.getP2().getName() + m.getDate().getTime());
                        if ( (m.getWinner().equals(m.getP1())) && (m.getP1().getStateMap().get(m.getDate()).getRanking() > m.getP2().getStateMap().get(m.getDate()).getRanking()) ) {
                            goodResult += 1;
                        }
                        else{
                            badResult += 1;
                        }
                    }
                    
                }
                
                if (bestGoodResult<goodResult){
                    bestF = F;
                    bestG = G;
                }
                System.out.println("Avec F = " + F + " et G = " + G + " On obtient " + goodResult + " bons résultats et " + badResult + " mauvais résultats");
            }
            
        }
            System.out.println("Meilleur combinaison : (F,G) = (" + bestF + "," + bestG + ") = " + goodResult + " sur " + (goodResult + badResult) + " matchs");
    }
    
    public static void simpleTest(){
        System.out.println("Let's play Tennis with Cassius and Simon ! ");
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData(3,2);
                           
        /*
                            *** Test One Player ranking's evolution
                                                               */
       if (false){
           Iterator it = simulationData.getPlayerByName("Yassine Idmbarek").getStateMap().entrySet().iterator();
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
                                
            
        if (true){
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
