package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import java.util.Random;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

public abstract class Enemy extends MoveableEntity {

  protected final Random behaviourDecider;

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
    randUpToSpeed(tick-1);
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
  protected void randUpToSpeed(Long toTick) {
    while (lastTicked() < toTick) {
      lastTick++;
      this.behaviourDecider.nextInt();
    }
  }
}
