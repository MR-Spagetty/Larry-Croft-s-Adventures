package test.nz.ac.wgtn.swen225.lc.fuzz;
import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.app.buttons.DefaultButton;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <p>  The fuzz testing class is responsible for finding bugs within gameplay.
 *      Only minor human intervention is required when running Fuzz test, which is to answer the prompt
 *      about if the player wants to enable recorder or not.
 * </p>
 *
 */
public class FuzzTest {

  @Test
  public void fuzzingTest() {
    Main.main(new String[0]);  //initialize and run main

    Map<String, PlayerAction> buttonsKBM = App.strokesToPlayerAction(); //list of objects that will store all the user keyboard inputs
    if (buttonsKBM.isEmpty()) {throw new IllegalArgumentException("no key binds detected");}
    Map<String, Runnable> buttonsGUI = App.strokesToUIAction();         //list of objects that will store all the user GUI inputs
    if(buttonsGUI.isEmpty()) {throw new IllegalArgumentException("no GUI buttons detected");}
    List<DefaultButton> startButtons = App.startMenuButtons();          //start button
    if(startButtons.isEmpty()){throw new IllegalArgumentException("no start button detected, cant start the game");}

    try{
      DefaultButton start = startButtons.get(0);                        //first button in the startButtons list is the game start button
      start.doClick();
      long startTime = System.currentTimeMillis();                      //takes in the current system time
      Random rand = new Random();
      while(System.currentTimeMillis() - startTime < 60000){            //run while loop for 1 minute
        List<String> keys = new ArrayList<>(buttonsKBM.keySet());
        String randKey = keys.get(rand.nextInt(keys.size()));
        PlayerAction nextAct = buttonsKBM.get(randKey);
        GameState.getGameState().getPlayer().queueAction(nextAct);      //queue the next action to be done
      }
      assert true;                                                      //assert true if there are no errors found during fuzzing
    }catch(Exception e) {
      throw new RuntimeException("Fuzz Testing failure, movement caused error: " + e.getMessage());
    }
    finally {
      System.out.println("Fuzzing complete, ran and closed");
    }
    /*
    *   TODO call the buttons that start the game into level 2
    */
  }
}
