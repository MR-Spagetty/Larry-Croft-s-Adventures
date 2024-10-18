package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * IceBoots allow the player to walk freely on ice disobeying the laws of ice
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class IceBoots extends Item {

  /**
   * creates a new set of ice boots at the given position and with the individual id
   *
   * @param location the position to create the boots at
   * @param individualID the individual it of the boots
   */
  public IceBoots(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new IceBoots(location, id);
  }
}
