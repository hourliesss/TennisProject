import java.util.ArrayList;
import java.io.File;

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
        File f = new File("data/tennis.csv");
        ReadData dataReader = new ReadData(f);
        SimulationData simulationData = dataReader.getSimulationData();
        System.out.println(simulationData.getMatches().get(53));
        System.out.println(simulationData.getMatches().get(53).getWinner());
    }
  
}
