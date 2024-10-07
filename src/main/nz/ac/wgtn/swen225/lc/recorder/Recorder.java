package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions and storing them in the defined json
 * style.
 */
public class Recorder {
    private String levelID;
    private List<PlayerAction> playerActions = new ArrayList<>();

  public Recorder(String levelID) {
    Objects.requireNonNull(levelID);
    if (levelID.isEmpty()) {
      throw new IllegalArgumentException("levelID cannot be empty");
    }

    this.levelID = levelID;
  }

  /**
   * Adds the playeraction into the playerActions list
   * 
   * @param a action to log
   */
  public void record(PlayerAction a) {
    Objects.requireNonNull(a);
    System.out.println(a);
    playerActions.add(a);
  }

  /*
   * Getter method for playerActions list
   * 
   * @return the playerAction List
   */
  public List<PlayerAction> playerActions() {
    return playerActions;
  }

  /**
   * Saves the recorded user inputs in the specified json format.
   * 
   * @param filename filename of this set of inputs and level ID.
   * @param path     path to save the file.
   */
  public void save(String filename, Path path) {
    // TODO: save file, finish when persistence module is completed
  }
}
