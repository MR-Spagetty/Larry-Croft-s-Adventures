package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

public class Fire extends AbstractTile {

  public Fire(Point location) {
    super(location);
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return enteree instanceof Player && super.canEnter(enteree);
  }

  @Override
  public void enter(Entity enteree) {
    super.enter(enteree);
    if (enteree instanceof Player p) {
      p.die();
    }
  }
}
