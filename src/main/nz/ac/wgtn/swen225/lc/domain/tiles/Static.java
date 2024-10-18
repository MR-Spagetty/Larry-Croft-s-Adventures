package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;

/**
 * the Static tile is a basic tile that Enemies can't enter
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public class Static extends Empty {
  /**
   * creates a new Static tile at the given position
   *
   * @param location the position to create the tile at
   */
  public Static(Point location) {
    super(location);
  }

  /** Entities may not enter otherwise same as {@link AbstractTile#canEnter(Entity)} */
  @Override
  public boolean canEnter(Entity enteree) {
    return !(enteree instanceof Enemy) && super.canEnter(enteree);
  }
}
