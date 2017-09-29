/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cassius
 */
public class RunnableRingo implements Runnable {
    
   private final Ringo ringo;
   private final int partition;
   
   public RunnableRingo(Ringo ringo, int partition) {
      this.ringo = ringo;
      this.partition = partition;
   }
   
   @Override
   public void run() {
       this.ringo.combinedTest(partition);
   }
}
