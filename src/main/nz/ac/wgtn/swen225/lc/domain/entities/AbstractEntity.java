package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * AbstractEntity is a abstract class implementing all the fields and methods that are common to all
 * entities
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public abstract class AbstractEntity implements Entity {

  /**
   * the lastTick this entity was ticked (-1 being never ticked)
   */
  protected long lastTick = -1;
  private Point location;
  private Maze maze = null;
  private final long individualID;

  /**
   * creates a new entity at the given position with the given individual id
   *
   * @param location the position to create the entity at
   * @param individualID the individual id of the entity
   */
  public AbstractEntity(Point location, long individualID) {
    this.location = location;
    this.individualID = individualID;
  }

  @Override
  public final long getUID() {
    return maze.longID() ^ individualID;
  }

  @Override
  public final long lastTicked() {
    return this.lastTick;
  }

  @Override
  public final Point location() {
    return this.location;
  }

  @Override
  public final void location(Point newLocation) {
    if (maze().getTile(newLocation).isEmpty()) {
      throw new IllegalArgumentException("Requested location is not valid in this maze");
    }
    this.location = newLocation;
  }

  @Override
  public final Maze maze() {
    return maze;
  }

  /**
   * correct implementation of maze setter
   *
   * <p>should only be overridden to set fields that require the maze immediately after it has been
   * set
   */
  @Override
  public void maze(Maze maze) {
    if (this.maze() != null) {
      throw new IllegalStateException("Maze may only be set once");
    }
    this.maze = maze;
  }

  @Override
  public JSONType toJson() {
    JSONObject out = new JSONObject();
    String type = getClass().getName();
    out.put("type", type.substring(type.lastIndexOf('.')+1));
    out.put("position", location().toJson());
    out.put("indID", this.individualID);
    return out;
  }

  @Override
  public abstract void tick(long tick);

  @Override
  public abstract boolean canTouch(Entity touchee);

  @Override
  public abstract void touch(Entity touchee);
}
