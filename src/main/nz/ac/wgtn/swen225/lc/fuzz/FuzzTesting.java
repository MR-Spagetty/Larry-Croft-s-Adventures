package nz.ac.wgtn.swen225.lc.fuzz;
import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import org.testng.annotations.Test;

import java.util.*;


public class FuzzTesting {

  @Test
  public void fuzzingTest() {
    Main.main(new String[0]);  //initialize and run main

    Map<String, PlayerAction> buttonsKBM = App.strokesToPlayerAction(); //variable that will store all the user keyboard inputs
    if (buttonsKBM.isEmpty()) {throw new IllegalArgumentException("no key binds detected");}
    Map<String, Runnable> buttonsGUI = App.strokesToUIAction();         //variable that will store all the user GUI inputs
    if(buttonsGUI.isEmpty()) {throw new IllegalArgumentException("no GUI buttons detected");}

    //TODO press the start button to get into the game level
    try{
      long startTime = System.currentTimeMillis();
      Random rand = new Random();
      while(System.currentTimeMillis() - startTime < 60000){           //run while loop for 5 minutes
        List<String> keys = new ArrayList<>(buttonsKBM.keySet());
        String randKey = keys.get(rand.nextInt(keys.size()));
        buttonsKBM.get(randKey);  //TODO buttonsKBM.get(randKey).playerAction.run or something
      }
      assert true;                                                      //assert true if there are no errors during fuzzing
    }catch(Exception e) {
      throw new RuntimeException("Fuzz Testing failure, movement caused error: " + e.getMessage());
    }
    finally {
      System.out.println("Fuzzing complete, ran for 1 minute");
    }

    /*
    *   TODO call the buttons that start the game into level 1
    *   TODO strategically spam call game buttons such as pause to test for any glitches
    */

  }

}
