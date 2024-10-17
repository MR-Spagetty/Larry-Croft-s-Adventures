package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

public abstract class Door extends AbstractTile {

  public Door(Point location) {
    super(location);
  }

  private boolean locked = false;

  /**
   * @return the locked state of the door
   */
  public boolean locked() {
    return this.locked;
  }

  /**
   * checks if the requirements to unlock this door are met
   *
   * @param enteree the entity whom may attempt to unlock this door
   * @return whether hte requirements are met or not
   */
  protected abstract boolean meetsUnlockReqs(Entity enteree);

  /**
   * performs any required actions associated with unlocking this door
   *
   * @param enteree the entity whom unlocked the door
   */
  protected abstract void onUnlock(Entity enteree);

  @Override
  public final boolean canEnter(Entity enteree) {
    return (!locked() || meetsUnlockReqs(enteree)) && super.canEnter(enteree);
  }

  @Override
  public final void enter(Entity enteree) {
    super.enter(enteree);
    if (locked()) {
      this.locked = false;
      onUnlock(enteree);
    }
  }
}
