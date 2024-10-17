package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * Enemy is an abstract class that defines the common behaviour, methods, and constants used by all
 * enemies
 */
public abstract class Enemy extends MoveableEntity {

  protected final Random behaviourDecider;

  /** the movements enemies are allowed to make */
  public static final List<Point> dirs =
      Stream.of(PlayerAction.values())
          .map(act -> act.offset)
          .filter(p -> !p.mul(2l).equals(p))
          .toList();

  /**
   * creates a new Enemy at the given position with the given individual ID and initialises its
   * random using its UID (see {@link Entity#getUID()})
   *
   * @param location the position to create the enemy at
   * @param individualID the individual id of the enemy to create
   */
  public Enemy(Point location, long individualID) {
    super(location, individualID);
    this.behaviourDecider = new Random(getUID());
  }

  @Override
  public void tick(long tick) {
    if (tick <= lastTicked()) {
      return;
    }
    // ensure the random has been ticked once for every tick that has already occurred
    randUpToSpeed(tick - 1);
    doBehaviour(tick);
    this.lastTick++;
    assert lastTicked() == tick;
  }

  /**
   * execute's enemy's behaviour
   *
   * @param tick the current tick
   */
  protected abstract void doBehaviour(long tick);

  @Override
  public boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  public void touch(Entity touchee) {
    if (!canTouch(touchee)) {
      throw new IllegalArgumentException(
          "Entity %d may not touch this Enemy".formatted(touchee.getUID()));
    }
  }

  /**
   * iterates the random to be up to date with the expected tick
   *
   * @param toTick the tick to update the random to
   */
  protected final void randUpToSpeed(Long toTick) {
    while (lastTicked() < toTick) {
      lastTick++;
      this.behaviourDecider.nextInt();
    }
  }

  public static Object fromJSON(JSONType json) {
    JSONObject data = (JSONObject) json;
    return switch (((JSONString) data.get("type")).get()) {
      case "Bug" -> Bug.fromJSON(json);
      case "BitFlipper" -> BitFlipper.fromJSON(json);
      // will not be reached within normal program
      default -> throw new IllegalArgumentException("Unknown Enemy type");
    };
  }
}
