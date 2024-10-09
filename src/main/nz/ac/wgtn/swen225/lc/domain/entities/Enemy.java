package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.Point;

public abstract class Enemy extends MoveableEntity {
  public Enemy(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  public void tick(long tick) {
    if (tick <= lastTicked()) {
      return;
    }
    this.lastTick = tick;
    doBehaviour(tick);
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'touch'");
  }
}
