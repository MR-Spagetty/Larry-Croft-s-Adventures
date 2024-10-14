package nz.ac.wgtn.swen225.lc.fuzz;

import nz.ac.wgtn.swen225.lc.app.*;

import javax.naDeveloper 4 <dev4@example.internal>.ldap.Control;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static nz.ac.wgtn.swen225.lc.app.App.getButtons;

/* inputTest takes in all the defined user inputs
 and randomly triggers them to test if anything is kill */
public class InputTest {
  List<DefaultButton> buttonsGUI = new ArrayList<>(); //variable that will store all the user GUI inputs
  List<DefaultButton> buttonsKBM = new ArrayList<>(); //variable that will store all the user keyboard inputs
  //TODO get buttonsKBM working, not sure what kind of data type this will take just yet

  void getGUIB(){                                    //getter method for grabbing the defined UI inputs in game
    buttonsGUI = getButtons();
  }
  void getKBMB(){
    //TODO grab keyboard inputs and into a list
    throw new UnsupportedOperationException("Keyboard input getter method is not implemented yet");
  }

  void guiButtonTester(){ //cycle through the entire list of userKeys with a try catch
    if(buttonsGUI.isEmpty()){
      throw new IllegalArgumentException("UserKeys are empty or undefined");
    }
    try{
      Collections.shuffle(buttonsGUI);    //randomises or shuffles the order of the buttons
      for(DefaultButton b : buttonsGUI){  //go through all the buttons
        b.doClick();      //click the button
      }
    }catch(IllegalArgumentException e) {
      throw new RuntimeException("Button Failure, something went wrong with");
    }

  }
  void kbmButtonTester(){
    if(buttonsKBM.isEmpty()){
      throw new IllegalArgumentException("UserKeys are empty or undefined");
    }
  }

}
