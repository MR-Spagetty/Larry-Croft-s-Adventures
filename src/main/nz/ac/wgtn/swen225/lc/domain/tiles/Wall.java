package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

/**
 * Walls are basic tiles that may not be occupied
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public record Wall(Point location) implements Tile {

  @Override
  public void leave(Entity exitee) {
    return;
  }
}
