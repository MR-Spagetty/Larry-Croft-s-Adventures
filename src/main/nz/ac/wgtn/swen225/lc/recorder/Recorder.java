package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Responsible for recording user actions across all levels of the game
 * and store them in the defined json file structure.
 * 
 * Each level is named numerically in the order they came in. e.g 1.json,
 * 2.json.
 * When saving, the current level has a link to the next level for ease of
 * reading and the final level will have a null.
 */
public class Recorder {
  private List<Level> allLevels = new ArrayList<>();
  private Level currentLevel;
  private Path dirPath;

  /*
   * Constructor of recorder
   * 
   * @param dirPath The directory to save the recorded level files in
   */
  public Recorder(Path dirPath) {
    Objects.requireNonNull(dirPath);
    this.dirPath = dirPath;
  }

  /**
   * updates the level to add an action to the actions list
   * 
   * @param a action to log
   */
  public void record(PlayerAction a) {
    Objects.requireNonNull(a);
    System.out.println(a);
    currentLevel.actions(a);
  }

  /*
   * Starts a level by creating a new level object and set it to currentLevel
   * 
   * @param levelPath The path to the levelfile, not to be confused with the recorder file
   */
  public void startLevel(Path levelPath) {
    Objects.requireNonNull(levelPath);
    String filename = (allLevels.size() + 1) + ".json";
    currentLevel = new Level(filename, new ArrayList<>(), Paths.get(dirPath.toString(), filename), levelPath);
  }

  /*
   * Ends the recording of a level by storing the current level in allLevels and
   * set currentLevel to null. This is done so that the user doesn't forget to
   * re-initilize when a new level starts.
   */
  public void endLevel() {
    allLevels.add(currentLevel);
    currentLevel = null;
  }

  /*
   * Ends the game recording by linking the level files with the next then saving
   * all level in json files.
   */
  public void endGame() {
    for (int level = 0; level < allLevels.size() - 1; level++) {
      Level currLevel = allLevels.get(level);
      Level nextLevel = allLevels.get(level + 1);
      currLevel.nextSavePath(nextLevel.savePath());
    }
    save();
  }

  /**
   * Saves the stored levels.
   */
  private void save() {
    allLevels.forEach(Level::save);
  }
}
