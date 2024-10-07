package nz.ac.wgtn.swen225.lc.domain;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import javax.swing.Timer;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

public final class GameState {

  /** default tick rate of the game in milliseconds */
  public static final int DEFAULT_TICK_RATE = 200;

  // TODO extend whatever JSONable interface is created for persistency
  private long tick = 0;
  private static GameState inst = new GameState();

  public static GameState getGameState() {
    return inst;
  }

  private String levelID = null;
  private Path levelPath = null;
  private Maze levelMaze = null;

  private Timer tickTimer = new Timer(DEFAULT_TICK_RATE, a -> tick());

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
    return getMaze().getEntities().parallelStream()
        .<Player>mapMulti(
            (e, cons) -> {
              if (e instanceof Player p) cons.accept(p);
            })
        .reduce(
            (p1, p2) -> {
              throw new IllegalStateException("Level contains more than one player");
            })
        .orElseThrow(() -> new IllegalStateException("Level does not contain a player"));
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
   * Sets the current level by its ID.
   *
   * @param levelID A string representing the ID of the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  public boolean setLevel(String levelID) {
    throw new UnsupportedOperationException("set level by ID NYI");
  }

  /**
   * Sets the current level by its path.
   *
   * @param levelPath A Path object representing the path to the level to be set.
   * @return A boolean value indicating whether the level was successfully set.
   */
  public boolean setLevel(Path levelPath) {
    throw new UnsupportedOperationException("set level by path NYI");
  }

  void initLevel(Maze level) {
    this.tickTimer.stop();
    this.levelMaze = level;
    this.tickTimer.restart();
  }

  static Maze setupLevel() {

    List<Entity> entities = List.of(new Player(Point.ORIGIN, 0));
    List<Tile> tiles = List.of(new Empty(Point.ORIGIN), new Empty(new Point(1, 0)));
    long maxTicks = 500;
    Maze maze = new Maze(maxTicks, "example", tiles, entities);
    entities.get(0).setMaze(maze);
    return maze;
  }
}
