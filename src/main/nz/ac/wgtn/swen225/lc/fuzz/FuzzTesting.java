package nz.ac.wgtn.swen225.lc.fuzz;
import nz.ac.wgtn.swen225.lc.app.*;
import nz.ac.wgtn.swen225.lc.domain.GameState;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class FuzzTesting {

  @Test
  public void fuzzingTest() {
    Main.main(new String[0]);  //initialize and run main

    List<DefaultButton> buttonsKBM = new ArrayList<>(); //variable that will store all the user keyboard inputs
    /*  TODO import the keyboard buttons into testing suite
    *   TODO call the buttons that start the game into level 1
    *   TODO strategically spam call keyboard direction buttons to test for out of bounds checking
    *   TODO strategically spam call game buttons such as pause to test for any glitches
    *
    */



  }



}
