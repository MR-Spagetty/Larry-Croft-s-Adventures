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
   * Gets the unique id of the entity for use in identification of the entity
   *
   * <p>primarily designed for use in randomisation of actions
   *
   * @return the unique id of this entity
   */
  long getUID();

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
  default void move(Point by) {
    Tile newLoc =
        getMaze()
            .getTile(getLocation().add(by))
            .orElseThrow(
                () -> new IllegalArgumentException("requested tile does not exist in maze"));
    Tile oldLoc =
        getMaze()
            .getTile(getLocation())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Maze does not contain tile this entity occupies"));
    if (!newLoc.canEnter(this)) {
      throw new IllegalArgumentException("Entity may not enter the requested tile");
    }
    oldLoc.leave(this);
    newLoc.enter(this);
  }

  /**
   * gets the maze that this Entity inhabits
   *
   * @return the inhabited maze
   */
  Maze getMaze();

  /**
   * sets the maze that this Entity inhabits
   *
   * <p>Should be only useable once
   *
   * @param maze the maze to inhabit
   */
  void setMaze(Maze maze);
}