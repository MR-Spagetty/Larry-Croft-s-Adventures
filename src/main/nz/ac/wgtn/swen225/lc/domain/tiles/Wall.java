package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Entity;
import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Walls are basic tiles that may not be occupied
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public record Wall(Point location) implements Tile {

  @Override
  public boolean canEnter(Entity enteree) {
    return false;
  }

  @Override
  public void enter(Entity enteree) {
    throw new UnsupportedOperationException("Entities may not enter a tile that may not be occupied");
  }

  @Override
  public void leave(Entity exitee) {
    throw new UnsupportedOperationException("Entities may not leave a tile that may not be occupied");
  }
}
