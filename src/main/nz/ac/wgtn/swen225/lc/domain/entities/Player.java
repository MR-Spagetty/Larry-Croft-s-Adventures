package nz.ac.wgtn.swen225.lc.domain.entities;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.tiles.MovementAffectorTile;

public class Player implements MoveableEntity {

  private Point actionQueue = Point.ORIGIN;

  private Consumer<Point> logger = a -> {};

  private Point location;
  private Maze maze;
  private final long individualID;
  private boolean dead = false;
  private boolean won = false;
  private Point lastMove = Point.ORIGIN;

  private long lastTick = -1;

  public Player(Point start, long indID) {
    this.individualID = indID;
    this.location = start;
  }

  public Player(Point start, long indID, Consumer<Point> logger) {
    this(start, indID);
    this.logger = logger;
  }

  /**
   * Queues an action for the player to use in the next tick
   *
   * @param newAction
   */
  public void queueAction(PlayerAction newAction) {
    this.actionQueue = newAction.offset;
  }

  @Override
  public Point lastMove() {
    return this.lastMove;
  }

  @Override
  public long lastTicked() {
    return this.lastTick;
  }

  @Override
  public void tick(long tick) {
    if (tick <= lastTicked()) {
      return;
    }
    Point move = this.actionQueue;
    if (maze.getTile(location()).get() instanceof MovementAffectorTile MET) {
      move = MET.affectMove(this, move);
    }
    this.lastMove = move;
    Point origin = location();
    try {
      move(this.actionQueue);
    } finally {
      Point locDelta = location().sub(origin);
      if (locDelta.equals(Point.ORIGIN)){
        this.lastMove = Point.ORIGIN;
      }
      this.logger.accept(locDelta);
      this.actionQueue = Point.ORIGIN;
    }
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

  @Override
  public boolean canTouch(Entity touchee) {
    return touchee instanceof Enemy;
  }

  @Override
  public void touch(Entity touchee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'touch'");
  }

  /** wins the level */
  public void win() {
    this.won = true;
  }

  /**
   * @return whether the level has be one yet
   */
  public boolean hasWon() {
    return this.won;
  }

  /** kills the player */
  public void die() {
    this.dead = true;
  }

  /**
   * @return whether the player is dead or not
   */
  public boolean isDead() {
    return this.dead;
  }
}
