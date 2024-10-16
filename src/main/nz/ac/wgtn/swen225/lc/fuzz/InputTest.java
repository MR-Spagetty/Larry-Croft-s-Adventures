package nz.ac.wgtn.swen225.lc.fuzz;

import nz.ac.wgtn.swen225.lc.app.keybinders.ControlKeys;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/* inputTest takes in all the defined user inputs
 and randomly triggers them to test if anything is kill */
public class InputTest {
  List<KeyEvent> userKeys = new ArrayList<>();  //variable that will store all the user inputs
  ControlKeys getControl(){ //getter method for grabbing the defined userKeys in game
    throw new UnsupportedOperationException("Not supported yet.");
    //TODO get the control keys and start using them lol??? daniel hasnt done anything for use yet so idk lol
    //TODO grab the control keys
  }

  void keyTester(){     //cycle through the entire list of userKeys with a try catch
    if(userKeys.isEmpty()){
      throw new IllegalArgumentException("UserKeys are empty or undefined");
    }

  }

}
