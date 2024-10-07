package nz.ac.wgtn.swen225.lc.recorder;

import java.util.List;
import java.nio.file.Path;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

/**
 * Represents the actions that the player makes in a level.
 */
public class Level {
  private String filename;
  private List<PlayerAction> actions;
  private Path filePath;
  private Path levelPath;
  private Path nextLevelPath;

  /**
   * Constructs a new Level with the specified parameters.
   *
   * @param filename      The name of the file associated with the level.
   * @param actions       A list of player actions representing the player's moves
   *                      in the level.
   * @param filePath      The path where the level is saved.
   * @param levelPath     The path of the current level.
   * @param nextLevelPath The path to the next level.
   */
  public Level(String filename, List<PlayerAction> actions, Path filePath, Path levelPath, Path nextLevelPath) {
    this.filename = filename;
    this.actions = actions;
    this.filePath = filePath;
    this.levelPath = levelPath;
    this.nextLevelPath = nextLevelPath;
  }

  public void updateNextLevel(Path nextLevel){
    nextLevelPath = nextLevel;
  }

  public void actions(PlayerAction a){
    actions.add(a);
  }

  /**
   * Saves the current state of the level.
   * This method needs to be implemented to handle saving functionality.
   */
  public void save() {
    // TODO: saves the level
  }
}