package nz.ac.wgtn.swen225.lc.recorder;

import java.util.Collections;
import java.util.List;
import java.nio.file.Path;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.persistency.*;

/**
 * Represents the actions that the player makes in a level.
 */
public class Level {
  private String filename;
  private List<PlayerAction> actions;
  private Path savePath;
  private Path levelPath;
  private Path nextSavePath;

  /**
   * Constructs a new Level with the specified parameters.
   *
   * @param filename      The name of the file associated with the level.
   * @param actions       A list of player actions representing the player's moves
   *                      in the level.
   * @param savePath      The path where the level is saved.
   * @param levelPath     The path of the current level.
   * @param nextSavePath The path to the next level.
   */
  public Level(String filename, List<PlayerAction> actions, Path savePath, Path levelPath) {
    this.filename = filename;
    this.actions = actions;
    this.savePath = savePath;
    this.levelPath = levelPath;
  }
  
  // Setters
  public void nextSavePath(Path p){ nextSavePath = p; }
  public void actions(PlayerAction a){ actions.add(a); }

  // Getters
  public Path savePath(){ return savePath; }
  public Path nextSavePath(){ return nextSavePath; }
  public List<PlayerAction> actions() { return Collections.unmodifiableList(actions); }

  /**
   * Saves the current state of the level.
   */
  public void save() {
    JSONObject json = new JSONObject();
    json.put("actions", toJSONList());
    json.put("level", new JSONString(levelPath.toString()));
    json.put("nextLevel", new JSONString(nextSavePath.toString()));
  }

  /*
   * Transforms the list of PlayerActions into a JsonList for saving
   */
  private JSONList toJSONList(){
    return new JSONList(
      actions.stream()
      .map(a -> (JSONType)(new JSONString(a.toString())))
      .toList()
      );
  }
}