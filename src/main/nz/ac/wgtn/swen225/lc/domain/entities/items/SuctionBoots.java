package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Suction boots allow the player to ignore conveyor tiles
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class SuctionBoots extends Item {

  /**
   * creates a new set of suction boots at the given position with the given individual id
   *
   * @param location
   * @param individualID
   */
  public SuctionBoots(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new SuctionBoots(location, id);
  }
}
