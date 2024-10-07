package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions and storing them in the defined json
 * style.
 * 
 * @param levelID level ID of the level corrosponding to the inputs.
 */
public class Recorder {
  private Path levelPath;
  private List<PlayerAction> playerActions = new ArrayList<>();

  public Recorder(Path path) {
    Objects.requireNonNull(path);
    this.levelPath = path;
  }

  /**
   * Adds the playeraction into the playerActions list
   * 
   * @param e key to log
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
    return Collections.unmodifiableList(playerActions);
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
