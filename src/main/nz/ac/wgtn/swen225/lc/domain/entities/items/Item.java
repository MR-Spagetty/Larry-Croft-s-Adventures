package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.AbstractEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;

public abstract class Item extends AbstractEntity implements JSONSerializable<Item>{
  public Item(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  public final boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  public final void touch(Entity touchee) {
    if (touchee instanceof Player p) {
      maze().getTile(location()).ifPresent(t -> t.leave(this));
      p.pickUp(this);
    }
    throw new IllegalArgumentException("Given entity may not touch items");
  }
}
