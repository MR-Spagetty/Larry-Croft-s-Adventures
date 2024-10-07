package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions across all levels of the game
 * and store them in the defined json file structure.
 */
public class Recorder {
  private List<PlayerAction> levelActions = new ArrayList<>();
  private Map<Integer, List<PlayerAction>> allActions = new HashMap<>();
  private Path dirPath;
  private int level = 1;

  public Recorder(Path dirPath) {
    Objects.requireNonNull(dirPath);
    this.dirPath = dirPath;
  }

  /**
   * Adds the playeraction into the playerActions list
   * 
   * @param a action to log
   */
  public void record(PlayerAction a) {
    Objects.requireNonNull(a);
    System.out.println(a);
    levelActions.add(a);
  }

  /*
   * Getter method for playerActions list
   * 
   * @return the playerAction List
   */
  public List<PlayerAction> playerActions() {
    return Collections.unmodifiableList(levelActions);
  }

  /*
   * Ends the recording of a level by storing the recorded actions and advance
   * level by one as well as clearing the levelActions.
   */
  public void endLevel() {
    allActions.put(level, levelActions);
    level++;
    levelActions.clear();
  }

  public void endGame() {

  }

  /**
   * Saves the recorded user inputs in the specified json format.
   * 
   * @param filename      Filename of this set of inputs and level ID.
   * @param path          The path of the current level.
   * @param nextLevelPath The path of the next level, can be null for end of game.
   * @param actions       The actions to save to file.
   */
  private void save(String filename, Path levelPath, Path nextLevelPath, List<PlayerAction> actions) {
    // TODO: save file, finish when persistence module is completed
  }
}
