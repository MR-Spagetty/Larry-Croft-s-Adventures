package nz.ac.wgtn.swen225.lc.domain;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

public final class GameState {
  // TODO extend whatever JSONable interface is created for persistency
  private long tick = 0;
  private static GameState inst = new GameState();

  public static GameState getGameState() {
    return inst;
  }

  private String levelID = null;
  private Path levelPath = null;
  private Maze levelMaze = null;

  private GameState() {}

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
    return getMaze().getEntities().stream()
        .<Player>mapMulti(
            (e, cons) -> {
              if (e instanceof Player p) cons.accept(p);
            })
        .findAny()
        .orElseThrow(() -> new IllegalStateException("Level does not contain a player"));
  }

  /**
   * Returns the current tick count of the game state.
   *
   * @return A long value representing the current tick count.
   */
  long getTick() {
    return this.tick;
  }

  /**
   * This method is responsible for updating the game state by one tick. It increments the internal
   * tick counter by one and performs necessary actions to update the game objects.
   *
   * @return void - This method does not return any value.
   */
  void tick() {
    getLevelID();
    // TODO tick game objects
    this.tick++;
    this.levelMaze.getEntities().forEach(e -> e.tick(getTick()));
  }

  /**
   * Sets the current level by its ID.
   *
   * @param levelID A string representing the ID of the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  boolean setLevel(String levelID) {
    throw new UnsupportedOperationException("set level by ID NYI");
  }

  /**
   * Sets the current level by its path.
   *
   * @param levelPath A Path object representing the path to the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  boolean setLevel(Path levelPath) {
    throw new UnsupportedOperationException("set level by path NYI");
  }

  static Maze setupLevel(Object TBD) {

    List<Entity> entities = null;
    List<Tile> tiles = null;
    long maxTicks = -1;
    Maze maze = new Maze(maxTicks, tiles, entities);

    throw new UnsupportedOperationException("NYI");
  }
}
