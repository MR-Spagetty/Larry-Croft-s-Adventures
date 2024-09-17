package nz.ac.wgtn.swen225.lc.domain;

/**
 * Basic interface that all entities in the game must implement.
 *
 * <p>This interface provides methods for managing entities' state, location, and game ticks.
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public interface Entity {
  // TODO extend whatever JSONable interface is created for persistency

  /**
   * Returns the timestamp of the last game tick when this entity was updated.
   *
   * @return the timestamp of the last game tick
   */
  long lastTicked();

  /**
   * Updates the state of this entity based on the given game tick.
   *
   * @param tick the current game tick
   */
  void tick(long tick);

  /**
   * Returns the current location of this entity in the game world.
   *
   * @return the current location of this entity
   */
  Point getLocation();

  void setLocation(Point newLocation);

  /**
   * Moves this entity by the specified amount in the given maze.
   *
   * @param by the amount to move this entity
   * @param in the maze in which to move this entity
   */
  default void move(Point by, Maze in) {
    Tile oldLoc =
        in.getTile(getLocation())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Given maze does not contain tile this entity occupies"));
    Tile newLoc =
        in.getTile(getLocation().add(by))
            .orElseThrow(
                () -> new IllegalArgumentException("requested tile does not exist in maze"));
    newLoc.enter(this);
    setLocation(getLocation().add(by));
    oldLoc.leave(this);
  }
}