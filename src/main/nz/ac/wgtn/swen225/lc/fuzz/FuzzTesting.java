package nz.ac.wgtn.swen225.lc.fuzz;
import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import org.testng.annotations.Test;

import java.util.*;


public class FuzzTesting {

  @Test
  public void fuzzingTest() {
    Main.main(new String[0]);  //initialize and run main

    Map<String, PlayerAction> buttonsKBM = App.strokesToPlayerAction();; //variable that will store all the user keyboard inputs
    Map<String, Runnable> buttonsGUI = App.strokesToUIAction();     //variable that will store all the user GUI inputs

    //TODO press the start button to get into the game level
    try{
      long startTime = System.currentTimeMillis();
      while(System.currentTimeMillis() - startTime < 300000){       //run while loop for 5 minutes
        Random rand = new Random();
        List<String> keys = new ArrayList<>(buttonsKBM.keySet());
        String randKey = keys.get(rand.nextInt(keys.size()));
        buttonsKBM.get(randKey);
      }
    }catch(Exception e) {
      throw new RuntimeException("Fuzz Testing failure, movement caused error");
    }

    /*
    *   TODO call the buttons that start the game into level 1
    *   TODO strategically spam call keyboard direction buttons to test for out of bounds checking
    *   TODO strategically spam call game buttons such as pause to test for any glitches
    */

  }

}
