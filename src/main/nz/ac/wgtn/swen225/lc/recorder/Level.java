package nz.ac.wgtn.swen225.lc.recorder;

import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.persistency.*;

/**
 * Represents the actions that the player makes in a level.
 */
public class Level {
  private String filename;
  private List<PlayerAction> actions;
  private Path savePath;
  private Path nextSavePath;
  private Path levelPath;

  /**
   * Constructs a new Level with the specified parameters. This is the full
   * version with all the data needed to save a level.
   *
   * @param filename     The name of the file associated with the level.
   * @param actions      A list of player actions representing the player's moves
   *                     in the level.
   * @param savePath     The path where the level is saved.
   * @param levelPath    The path of the current level.
   * @param nextSavePath The path to the next level.
   */
  public Level(String filename, List<PlayerAction> actions, Path savePath, Path levelPath) {
    this.filename = filename;
    this.actions = actions;
    this.savePath = savePath;
    this.levelPath = levelPath;
  }

  /**
   * Constructs a Level with only action and nextSavePath. This is meant to be
   * used for parser to return the data needed to replay a level. The package
   * public acess level is intentional.
   *
   * @param actions       The actions that the player took in a level.
   * @param nextSavePath  The path to the next Level.
   */
  Level(List<PlayerAction> actions, Path nextSavePath, Path levelPath) {
    this.actions = actions;
    this.nextSavePath = nextSavePath;
    this.levelPath = levelPath;
  }

  // Setters
  public void nextSavePath(Path p) { nextSavePath = p; }
  public void actions(PlayerAction a) { actions.add(a); }

  // Getters
  public Path savePath() { return savePath; }
  public Path nextSavePath() { return nextSavePath; }
  public Path levelPath(){ return levelPath; }
  public String filename() { return filename; }
  public List<PlayerAction> actions() { return Collections.unmodifiableList(actions); }

  /**
   * Saves the current state of the level.
   */
  public void save() {
    JSONObject json = new JSONObject();
    json.put("actions", toJSONList());
    json.put("level", new JSONString(levelPath.toString()));
    json.put("nextLevel", new JSONString(nextSavePath.toString()));

    try{
      Persistency.saveToFile(json, Paths.get(savePath.toString(), filename).toString());
    } catch(IOException e){
      throw new Error(e);
    }
  }

  /**
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