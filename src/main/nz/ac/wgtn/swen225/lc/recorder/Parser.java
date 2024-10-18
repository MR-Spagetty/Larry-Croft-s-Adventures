package nz.ac.wgtn.swen225.lc.recorder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.persistency.*;

/**
 * The Parser class provides methods for parsing game levels from a JSON file.
 * It handles extracting player actions, next level path, and level data from the JSON content.
 * 
 * @author Developer 4 <dev4@example.internal> 300655226
 */
public class Parser {
  private static Map<String, PlayerAction> mapper = Map.of(
    PlayerAction.Up.toString(),     PlayerAction.Up,
    PlayerAction.Down.toString(),   PlayerAction.Down,
    PlayerAction.Left.toString(),   PlayerAction.Left,
    PlayerAction.Right.toString(),  PlayerAction.Right,
    PlayerAction.None.toString(),   PlayerAction.None
  );
  
  /**
   * Parses the level data from a given JSON file.
   *
   * @param p the file path to the JSON file
   * @return a Level object containing player actions, the next level path, and the current level path
   */
  public static Level parse(Path p) {
    JSONObject jsonContent = openJSON(p);
    List<PlayerAction> actions = parseActions(jsonContent);
    Path nextLevel = parseNextLevel(jsonContent);
    Path levelPath = parseLevel(jsonContent);
    return new Level(actions, nextLevel, levelPath);
  }

  /**
   * Extracts the player actions from the parsed JSON content.
   *
   * @param j the JSON content representing the level
   * @return a list of PlayerAction objects
   */
  private static List<PlayerAction> parseActions(JSONObject j) {
    List<JSONType> actionJSON = ((JSONList)j.get("actions")).getElements();
    return actionJSON.stream()
            .map(a->((JSONString)a).value())
            .map(a->mapper.get(a))
            .toList();
  }

  /**
   * Extracts the next level path from the parsed JSON content.
   *
   * @param j the JSON content representing the level
   * @return a Path object pointing to the next level file
   */
  private static Path parseNextLevel(JSONObject j) {
    String path = ((JSONString)j.get("nextLevel")).value();
    return Paths.get(path);
  }

  /**
   * Extracts the current level path from the parsed JSON content.
   * 
   * @param j the JSON content representing the level
   * @return a Path object pointing to the current level file
   */
  private static Path parseLevel(JSONObject j) {
    String path = ((JSONString)j.get("level")).value();
    return Paths.get(path);
  }

  /**
   * Opens a JSON file and loads its content.
   *
   * @param p the file path to the JSON file
   * @return a JSONObject representing the content of the JSON file
   * @throws Error if an I/O error occurs during file reading
   */
  private static JSONObject openJSON(Path p) {
    try {
      JSONType t = Persistency.loadFromFile(p.toString());
      return (JSONObject) t;
    } catch(IOException e) {
      throw new Error(e);
    }
  }
}