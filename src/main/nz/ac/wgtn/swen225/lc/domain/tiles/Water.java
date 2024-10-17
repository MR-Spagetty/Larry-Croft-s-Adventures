package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableBlock;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Flippers;

public class Water extends AbstractTile {

  private boolean filled = false;

  public Water(Point location) {
    super(location);
  }

  public boolean filled() {
    return filled;
  }

  @Override
  public void enter(Entity enteree) {
    if (!this.filled && (enteree instanceof MoveableBlock)) {
      this.filled = true;
      return;
    } else if (!this.filled
        && (enteree instanceof Player p)
        && p.getInventory().parallelStream().noneMatch(i -> i instanceof Flippers)) {
      p.die();
    }
    super.enter(enteree);
  }
}
