package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Entity;
import nz.ac.wgtn.swen225.lc.domain.Player;
import nz.ac.wgtn.swen225.lc.domain.Point;

public class Exit extends Empty {

  /**
   * Creates a new Exit Tile at the given location
   *
   * @param location the location to create the tile at
   */
  public Exit(Point location) {
    super(location);
  }

  @Override
  public void enter(Entity enteree) {
    if (enteree instanceof Player p) {
      // TODO finish level
    }
    super.enter(enteree);
  }
}
