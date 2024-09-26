package nz.ac.wgtn.swen225.lc.domain.entities;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;

public class Player implements MoveableEntity {

  private PlayerAction actionQueue = PlayerAction.None;

  private Consumer<Point> logger = a -> {};

  private Point location;
  private Maze maze;
  private final long individualID;
  private List<Item> inventory = new ArrayList<>();

  private long lastTick = -1;

  public Player(Point start, long indID) {
    this.individualID = indID;
    this.location = start;
  }

  public Player(Point start, long indID, Consumer<Point> logger) {
    this(start, indID);
    this.logger = logger;
  }

  @Override
  public long lastTicked() {
    return this.lastTick;
  }

  @Override
  public void tick(long tick) {
    if (tick <= lastTicked()) {
      logger.accept(new Point(0, 0));
    }
    Point origin = location();
    move(actionQueue.offset);
    logger.accept(location().sub(origin));
    actionQueue = PlayerAction.None;
  }

  @Override
  public Point location() {
    return this.location;
  }

  @Override
  public void location(Point newLocation) {
    if (getMaze().getTile(newLocation).isEmpty()) {
      throw new IllegalArgumentException("Requested location is not valid in this maze");
    }
    this.location = newLocation;
  }

  @Override
  public Maze getMaze() {
    return this.maze;
  }

  @Override
  public void setMaze(Maze maze) {
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
  public long getUID() {
    return maze.longID() ^ individualID;
  }

  /**
   * Pickup the given item
   *
   * @param toPickup the item to pickup
   */
  public void pickUp(Item toPickup) {
    this.inventory.add(toPickup);
  }

  /**
   * clear the player's inventory
   *
   * <p>primarily for use by the thief tile
   */
  public void clearInventory() {
    this.inventory.clear();
  }

  /**
   * Gets the players inventory
   *
   * <p>gets an unmodifiable view of the player's inventory primarily for use in displaying the
   * inventory to the user
   *
   * @return the player's inventory
   */
  public List<Item> getInventory() {
    return Collections.unmodifiableList(this.inventory);
  }

  @Override
  public boolean canTouch(Entity touchee) {
    return touchee instanceof Enemy;
  }

  @Override
  public void touch(Entity touchee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'touch'");
  }
}
