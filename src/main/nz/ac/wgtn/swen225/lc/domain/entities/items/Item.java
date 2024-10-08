package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

public interface Item extends Entity {
  @Override
  default boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  default void touch(Entity touchee) {
    if (touchee instanceof Player p) {
      getMaze().getTile(location()).ifPresent(t -> t.leave(this));
      p.pickUp(this);
    }
    throw new IllegalArgumentException("Given entity may not touch items");
  }
}
