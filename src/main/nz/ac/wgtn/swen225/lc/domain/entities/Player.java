package nz.ac.wgtn.swen225.lc.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.MovementAffecterTile;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;

public class Player extends MoveableEntity {

  private Point actionQueue = Point.ORIGIN;

  private Consumer<Point> logger = a -> {};

  private List<Item> inventory = new ArrayList<>();
  private boolean dead = false;
  private boolean won = false;

  public Player(Point start, long indID) {
    super(start, indID);
  }

  public Player(Point start, long indID, Consumer<Point> logger) {
    super(start, indID);
    this.logger = logger;
  }

  @Override
  public void tick(long tick) {
    if (tick <= lastTicked()) {
      return;
    }
    this.lastTick = tick;
    Point move = this.actionQueue;
    if (maze().getTile(location()).get() instanceof MovementAffecterTile MET) {
      move = MET.affectMove(this, move);
    }
    this.lastMove = move;
    Point origin = location();
    try {
      move(this.actionQueue);
    } finally {
      Point locDelta = location().sub(origin);
      if (locDelta.equals(Point.ORIGIN)) {
        this.lastMove = Point.ORIGIN;
      }
      this.logger.accept(locDelta);
      this.actionQueue = Point.ORIGIN;
    }
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

  /**
   * Queues an action for the player to use in the next tick
   *
   * @param newAction
   */
  public void queueAction(PlayerAction newAction) {
    this.actionQueue = newAction.offset;
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

  public static Player fromJSON(JSONObject json){
    return new Player(Point.fromJSON(json.get("position")), 0);
  }
}
