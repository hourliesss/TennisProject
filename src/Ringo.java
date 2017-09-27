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
    public static void main(String[] args){
        System.out.println("Let's play Tennis with Cassius and Simon ! ");
        File f = new File("data/tennis2.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData();
        Iterator it = simulationData.getPlayers().get(1).getStateMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
       
       
    }
  
}
