package nz.ac.wgtn.swen225.lc.domain;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import javax.swing.Timer;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Treasure;
import nz.ac.wgtn.swen225.lc.domain.tiles.ModifiableTile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.persistency.JSONList;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

public final class GameState {

  /** default tick rate of the game in milliseconds */
  public static final int DEFAULT_TICK_RATE = 200;

  private long tick = 0;
  private static GameState inst = new GameState();

  public static GameState getGameState() {
    return inst;
  }

  private String levelID = null;
  private Path levelPath = null;
  private Maze levelMaze = null;

  public Timer tickTimer = new Timer(DEFAULT_TICK_RATE, a -> tick());

  {
    this.tickTimer.setRepeats(true);
  }

  private GameState() {}

  /**
   * checks if the level has been one
   *
   * @return whether the level has been won
   */
  public boolean hasWon() {
    return getPlayer().hasWon();
  }

  /**
   * checks if the level has been lost
   *
   * @return whether the level has been lost
   */
  public boolean hasLost() {
    return (this.tick >= this.levelMaze.maxTicks) ? true : getPlayer().isDead();
  }

  /**
   * Returns the ID of the current level.
   *
   * @return A string representing the ID of the current level.
   */
  String getLevelID() {
    return Objects.requireNonNull(this.levelID, "level not initialised");
  }

  /**
   * Returns the path to the current level.
   *
   * @return A Path object representing the path to the current level.
   */
  Path getLevelPath() {
    return Objects.requireNonNull(this.levelPath, "level not initialized");
  }

  public Maze getMaze() {
    return Objects.requireNonNull(this.levelMaze, "level not initialized");
  }

  public Player getPlayer() {
    getLevelID();
    return getMaze().getPlayer();
  }

  public int requiredTreasures() {
    getLevelID();
    return this.levelMaze.requiredTreasures();
  }

  public int collectedTreasures() {
    return (int) getPlayer().getInventory().stream().filter(i -> i instanceof Treasure).count();
  }

  /**
   * Returns the current tick count of the game state.
   *
   * @return A long value representing the current tick count.
   */
  public long getTick() {
    return this.tick;
  }

  /**
   * This method is responsible for updating the game state by one tick. It increments the internal
   * tick counter by one and performs necessary actions to update the game objects.
   *
   * @return void - This method does not return any value.
   */
  public void tick() {
    getLevelID();
    this.tick++;
    this.levelMaze.getEntities().forEach(e -> e.tick(getTick()));
  }

  /**
   * Sets the current level by its path.
   *
   * @param levelPath A Path object representing the path to the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  public boolean setLevel(Path levelPath) {
    this.levelID = null;
    this.levelPath = null;
    try {
      initLevel(Maze.fromJSON(Persistency.loadFromFile(levelPath)));
      this.levelID = this.levelMaze.ID;
      this.levelPath = levelPath;
      this.tick = 0;
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public boolean loadState(Path savePath) {
    try {
      JSONType json = Persistency.loadFromFile(savePath);
      if (!(json instanceof JSONObject)) {
        throw new IllegalArgumentException("Expected JSONObject got " + json.getClass().getName());
      }
      JSONObject data = (JSONObject) json;
      JSONType levelPath = data.get("level");
      if (!(levelPath instanceof JSONString)) {
        throw new IllegalArgumentException(
            "Expected JSONString at \"level\" but found " + levelPath.getClass().getName());
      }
      JSONType levelData = Persistency.loadFromFile(Path.of(((JSONString) levelPath).get()));
      if (!(levelData instanceof JSONObject)) {
        throw new IllegalArgumentException(
            "Expected JSONObject but found " + levelData.getClass().getName());
      }
      JSONType modTileData = data.get("modTiles");
      if (!(modTileData instanceof JSONList)) {
        throw new IllegalArgumentException(
            "Expected JSONList at \"modTiles\" but found " + modTileData.getClass().getName());
      }
      JSONType entityData = data.get("entities");
      if (!(entityData instanceof JSONList)) {
        throw new IllegalArgumentException(
            "Expected JSONList at \"entities\" but found " + entityData.getClass().getName());
      }
      initLevel(
          Maze.fromJSONState(
              ((JSONList) modTileData).getElements().stream().map(Tile::fromJSON).toList(),
              ((JSONList) entityData).getElements().stream().map(Entity::fromJSON).toList(),
              (JSONObject) levelData));
      this.levelID = this.levelMaze.ID;
      this.levelPath = Path.of(((JSONString) levelPath).get());

      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public boolean saveState(Path savePath) {
    JSONObject out = new JSONObject();
    out.put("level", getLevelPath().toString());
    out.put("tick", getTick());
    JSONList modifiableTiles = new JSONList();
    this.levelMaze.getModifiableTiles().parallelStream()
        .map(ModifiableTile::toJson)
        .forEach(modifiableTiles::add);
    out.put("modTiles", modifiableTiles);
    JSONList entities = new JSONList();
    this.levelMaze.getEntities().stream().map(Entity::toJson).forEach(entities::add);
    out.put("entities", entities);
    try {
      Persistency.saveToFile(out, savePath);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  void initLevel(Maze level) {
    this.tickTimer.stop();
    this.levelMaze = level;
    this.tickTimer.restart();
  }
}
