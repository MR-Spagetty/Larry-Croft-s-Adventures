package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.event.KeyEvent;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions and storing them in the defined json style.
 * 
 * @param levelID level ID of the level corrosponding to the inputs.
 */
public class Recorder {
  private String levelID;
  private List<PlayerAction> playerActions = new ArrayList<>();

<<<<<<< HEAD
  public Recorder(String levelID) {
=======
  Recorder(String levelID) {
>>>>>>> fb9601ec7aee6670f7605ff639f4c1758267c9f6
    Objects.requireNonNull(levelID);
    if (levelID.isEmpty()) {
      throw new IllegalArgumentException("levelID cannot be empty");
    }
    this.levelID = levelID;
  }

  /**
<<<<<<< HEAD
   * Map the keyevent to player action then push into playerActions List
   * 
   * @param e key to log
   */
  public void record(KeyEvent e) {
    // TODO: once App creates the mapper class/method/whatever
  }

  /**
=======
>>>>>>> fb9601ec7aee6670f7605ff639f4c1758267c9f6
   * Adds the playeraction into the playerActions list
   * 
   * @param e key to log
   */
  public void record(PlayerAction a) {
    System.out.println(a);
    playerActions.add(a);
  }

  /**
   * Saves the recorded user inputs in the specified json format.
   * 
   * @param filename filename of this set of inputs and level ID.
   * @param path path to save the file.
   */
  public void save(String filename, Path path) {
    // TODO: save file, finish when persistence module is completed

  }
}
