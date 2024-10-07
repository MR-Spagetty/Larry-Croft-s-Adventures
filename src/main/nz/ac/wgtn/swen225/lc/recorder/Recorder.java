package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions across all levels of the game
 * and store them in the defined json file structure.
 */
public class Recorder {
  private List<Level> allLevels = new ArrayList<>();
  private Level currentLevel;
  private Path dirPath;

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
    currentLevel.actions(a);
  }

  /*
   * Ends the recording of a level by storing the recorded actions and advance
   * level by one as well as clearing the levelActions.
   */
  public void endLevel() {
    allLevels.add(currentLevel);
  }

  public void endGame() {

  }

  /**
   * Saves the stored levels.
   */
  private void save() {
    // TODO: save file, finish when persistence module is completed
  }
}
