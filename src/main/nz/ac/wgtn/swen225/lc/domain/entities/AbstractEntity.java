package nz.ac.wgtn.swen225.lc.domain.entities;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public abstract class AbstractEntity implements Entity {

  protected long lastTick = -1;
  private Point location;
  private Maze maze;
  private final long individualID;

  /**
   * @param location
   * @param maze
   * @param individualID
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

  @Override
  public final void maze(Maze maze) {
    this.maze = maze;
    try {
      Field mazeField = this.getClass().getField("maze");
      Field modifiersField = Field.class.getDeclaredField("modifiers");
      modifiersField.setAccessible(true);
      modifiersField.setInt(mazeField, mazeField.getModifiers() | Modifier.FINAL);
    } catch (NoSuchFieldException NSF) {
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  @Override
  public JSONType toJson() {
    JSONObject out = new JSONObject();
    out.put("type", getClass().getName());
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
