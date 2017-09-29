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
    
    
    
    public static ArrayList<Integer> fillList(Player p,int i, ArrayList<Integer> list, ArrayList<Player> players,ArrayList<Integer> results, Integer good){
            if (list.size() == 0){
                list.add(i);
                players.add(p);
                results.add(good);
            }
            else{
                if (list.get(list.size()-1) < i){
                    list.add(i);
                    players.add(p);
                    results.add(good);
                }
                else{
                    boolean stop = false;
                    int index1 = 0;
                    while (stop == false){
                        if (list.get(index1) < i){
                            index1++;
                        }
                        else{
                            stop = true;
                        }
                    }
                    list.add(index1, i);
                    players.add(index1,p);
                    results.add(index1,good);
                }
               
            }
    return list;
    }
    
    
    
    public static void testRatio(){
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        int badResult=0; int goodResult=0;
        int bestF = 0; int bestG = 0; 
        int bestGoodResult = 0; 
        for (int F = 1; F < 10 ; F++){
            for (int G = 0; G < 10;G++){
                badResult = 0; goodResult = 0;
                SimulationData simulationData = dataReader.getSimulationData(F,G,10);
                int count = 0;
                for(Player p : simulationData.getPlayers()){
                    
                    if (p.getMatches().size() > 10 ){ //Wait for ranking to set a correct value
                        count++;
                        for (int i = 10;i<p.getMatches().size();i++){
                            if (p.getMatches().get(i).getP2().getStateMap().size() >= 10){
                                int pos = 0; //Does the oppenent already play 10 matches?
                                for (Calendar key : p.getMatches().get(i).getP2().getStateMap().keySet()) {

                                    if(key.before(p.getMatches().get(i).getDate())){
                                        pos++;
                                        
                                    }
                                }

                                if (pos>10){
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
                System.out.println(count + " joueurs ont joué plus de 30 matchs");
                System.out.println("Avec F = " + F + " et G = " + G + " On obtient " + goodResult + " bons résultats et " + badResult + " mauvais résultats");
            }
            
        }
            System.out.println("Meilleur combinaison : (F,G) = (" + bestF + "," + bestG + ") = " + bestGoodResult/2 + " sur " + (goodResult + badResult)/2 + " matchs");
    }
    
    
    public static int healthTest(int coeffF, int coeffG){
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData(coeffF,coeffG,10);
        ArrayList<Integer> matchsNumber = new ArrayList();
        ArrayList<Player> players = new ArrayList();
        int goodResult;
        ArrayList<Integer> goodResults = new ArrayList();
        //// Select players who played at least 10 matches and order them by number of match ascending
        for (int i = 0; i< simulationData.getPlayers().size();i++){
                     Player p = simulationData.getPlayers().get(i);
                     if (p.getMatches().size() > 10 ){
                        
                        goodResult = 0;
                        int compt = 0;
                        for (TennisMatch m : p.getMatches()){
                             if (compt < 10){
                                 compt++;
                             }
                             else{
                                if (m.getP2().getStateMap().size() >= 10){
                                   int pos = 0; //Does the oppenent already play 10 matches?
                                   for (Calendar key : m.getP2().getStateMap().keySet()) {

                                       if(key.before(m.getDate())){
                                           pos++;

                                       }
                                   }

                                   if (pos>10){
                                       if ( ( (m.getWinner().equals(p)) && (p.getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(m.getDate()).getHealth()) >  m.getP2().getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(m.getP2().getStateMap().get(m.getDate()).getHealth()))) || ( (!m.getWinner().equals(p)) && (p.getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(m.getDate()).getHealth()) <  m.getP2().getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(m.getP2().getStateMap().get(m.getDate()).getHealth()))) ) {
                                                 goodResult++;
                                       }
                                   }

                               }   
                            }
                        }
                        matchsNumber = fillList(p,p.getMatches().size(),matchsNumber,players,goodResults,goodResult);
                     }
        }
                            
        //System.out.println(players);
        //System.out.println(matchsNumber);
        //System.out.println("good results :" + goodResults.stream().mapToInt(Integer::intValue).sum() + " sur " + matchsNumber.stream().mapToInt(Integer::intValue).sum());
        
        // Setting Health coeff to each of these players at 2 
        ArrayList<Integer> healthCoeffs = new ArrayList();
        for (int i=0;i<players.size();i++){
            healthCoeffs.add(i,10);
        }
        for (int p = 0; p < players.size();p++){
            players.get(p).newStateMap();
            for (TennisMatch m : players.get(p).getMatches()){
                players.get(p).updateRanking(m.getDate(), 100, 5, 1, healthCoeffs.get(p));
            }
        }
        
        ArrayList<Integer> coeffsCopy = new ArrayList();
        
        while (!coeffsCopy.equals(healthCoeffs)){
            coeffsCopy = new ArrayList(healthCoeffs);
            for (int j = 0;j<players.size();j++){
            Player p = players.get(j);
           // System.out.println("Nous sommes au " + j + " eme joueur sur " + players.size());
            for (int i = 1; i<20;i++){
                goodResult = 0;
                int compt = 0;
                 for (TennisMatch m : p.getMatches()){
                        p.updateRanking(m.getDate(), 100, coeffF, coeffG, i);
                        if (compt<10){
                            compt++;
                        }
                        else{
                                 if (m.getP2().getStateMap().size() >= 10){
                                    int pos = 0; //Does the oppenent already play 10 matches?
                                    for (Calendar key : m.getP2().getStateMap().keySet()) {

                                        if(key.before(m.getDate())){
                                            pos++;

                                        }
                                    }

                                    if (pos>10){
                                        if ( ( (m.getWinner().equals(p)) && (p.getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(m.getDate()).getHealth()) >  m.getP2().getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(m.getP2().getStateMap().get(m.getDate()).getHealth()))) || ( (!m.getWinner().equals(p)) && (p.getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(m.getDate()).getHealth()) <  m.getP2().getStateMap().get(m.getDate()).getRanking()*Player.healthFunction(m.getP2().getStateMap().get(m.getDate()).getHealth()))) ) {
                                                  goodResult++;
                                        }
                                    }

                                }   
                            }
                    }
                   if (goodResult>goodResults.get(j)){
                      goodResults.set(j, goodResult);
                      healthCoeffs.set(j,i);
                   }
               }
           }
        
        }
        System.out.println("A la fin on a : " + goodResults.stream().mapToInt(Integer::intValue).sum());
       
        
            
     return goodResults.stream().mapToInt(Integer::intValue).sum(); 
    }
    
    public void combinedTest(int partition){
        int bestF = 0; int bestG = 0; 
        int bestGoodResult = 0; int goodResult; 
        if (partition != -1) {
             for (int F = partition; F < partition +1; F++){
                  for (int G = 1; G < 10;G++){
                    System.out.println("F = " + F + " , G = " + G);
                    goodResult = healthTest(F,G);
                    if (goodResult > bestGoodResult){
                        bestGoodResult = goodResult;
                       bestF = F;
                       bestG = G;
                    }
              }
          }
        }
        else {
            for (int F = 1; F < 10; F++){
             for (int G = 1; G < 10; G++){
                System.out.println("F = " + F + " , G = " + G);
                goodResult = healthTest(F,G);
                if (goodResult > bestGoodResult){
                    bestGoodResult = goodResult;
                    bestF = F;
                    bestG = G;
                }
            }
        }
        }
        int matchN = 0;
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData(1,1,10);
         for (Player p : simulationData.getPlayers()){
                int compt = 0;
                 for (TennisMatch m : p.getMatches()){
                        if (compt<10){
                            compt++;
                        }
                        else{
                                 if (m.getP2().getStateMap().size() >= 10){
                                    int pos = 0; //Does the oppenent already play 10 matches?
                                    for (Calendar key : m.getP2().getStateMap().keySet()) {

                                        if(key.before(m.getDate())){
                                            pos++;

                                        }
                                    }

                                    if (pos>10){
                                        matchN++;
                                    }

                                }   
                            }
                    }
                  
               
           }
        
        
        System.out.println("Resultat final : " + bestGoodResult + "sur " + matchN + " avec (F,G) = (" + bestF + "," + bestG + ")");
    }
    
    public static void simpleTest(){
        System.out.println("Let's play Tennis with Cassius and Simon ! ");
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData(5,1,10);
                           
        /*
                            *** Test One Player ranking's evolution
                                                               */
       if (false){
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
        if (true){
            int count = 0; 
            ArrayList<Integer> matchsNumber = new ArrayList();
            ArrayList<Player> players = new ArrayList();
            ArrayList<Integer> results = new ArrayList();
            int good;
                for (Player p : simulationData.getPlayers()){
                     if (p.getMatches().size() > 10 ){ //Wait for ranking to set a correct value
                                count++;
                                good = 0;
                                System.out.println("----- Joueur :  " + p.getName() + " ------ ");
                                for (int i = 10;i<p.getMatches().size();i++){
                                    if (p.getMatches().get(i).getP2().getStateMap().size() >= 30){

                                        int pos = 0; //Does the oppenent already play 10 matches?
                                        for (Calendar key : p.getMatches().get(i).getP2().getStateMap().keySet()) {

                                            if(key.before(p.getMatches().get(i).getDate())){
                                                pos++;

                                            }
                                        }

                                        if (pos>30){
                                            if ( (p.getMatches().get(i).getWinner().equals(p)) && (p.getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(p.getMatches().get(i).getDate()).getHealth()) >  p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getHealth())) || (!p.getMatches().get(i).getWinner().equals(p)) && (p.getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getStateMap().get(p.getMatches().get(i).getDate()).getHealth()) <  p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getRanking()*Player.healthFunction(p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getHealth())) ) {
                                               if(p.getMatches().get(i).getWinner().equals(p)){
                                                   good++;
                                                   System.out.println(p.getMatches().get(i).getDate().getTime() + " : Bon resultat :" + p.getName() + "(" +p.getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") a battu " + p.getMatches().get(i).getP2().getName() + " (" + p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") à " + " à " + p.getMatches().get(i).getTournament().getName());
                                               }
                                               else{
                                                   good++;
                                                   System.out.println(p.getMatches().get(i).getDate().getTime() + " : Bon resultat :" + p.getName() + "(" +p.getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") a perdu contre " + p.getMatches().get(i).getP2().getName() + " (" + p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") à " + " à " + p.getMatches().get(i).getTournament().getName());

                                               }
                                           }
                                           else{
                                                if(p.getMatches().get(i).getWinner().equals(p)){
                                                   System.out.println(p.getMatches().get(i).getDate().getTime() + " : Mauvais resultat :" + p.getName() + "(" +p.getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") a battu " + " (" + p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") à " + p.getMatches().get(i).getP2().getName() + " à " + p.getMatches().get(i).getTournament().getName());
                                               }
                                               else{
                                                   System.out.println(p.getMatches().get(i).getDate().getTime() + " : Mauvais resultat :" + p.getName() + "(" +p.getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") a perdu contre " + p.getMatches().get(i).getP2().getName() + " (" + p.getMatches().get(i).getP2().getStateMap().get(p.getMatches().get(i).getDate()).getAtpRanking() + ") à " + p.getMatches().get(i).getTournament().getName());

                                               }
                                          }
                                        }
                                    }
                            }
                                 matchsNumber = fillList(p,p.getMatches().size(),matchsNumber,players,results,good);
                                
                            }
        }
        System.out.println(count + " joueurs ont joué plus de 30 matchs");
        System.out.println(matchsNumber);
        System.out.println(players);
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
            
        
        
        
        
    }
    
}
  
}
