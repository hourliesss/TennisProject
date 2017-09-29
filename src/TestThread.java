
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cassius
 */
public class TestThread {
    public static void main(String[] args) {
        
      ArrayList<Thread> list = new ArrayList<>();
      ArrayList<Runnable> listRun = new ArrayList<>();
      
      for (int i = 0; i < 3 ; i++) {
          listRun.add(new RunnableRingo(new Ringo(), i));
      }
      
      for (int i = 0; i <3 ; i++) {
          list.add(new Thread(listRun.get(i)));
          list.get(i).setDaemon(false);
          list.get(i).setName("ringo thread number " + i);
          System.out.println("starting ringo thread number " +i);
          list.get(i).start();
      }
              
    }
}
