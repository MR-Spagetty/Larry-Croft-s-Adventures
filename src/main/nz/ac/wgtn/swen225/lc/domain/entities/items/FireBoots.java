package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Fire boots allow the player to walk on fire tiles without dying
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public class FireBoots extends Item {

  /**
   * creates a new set of fire boots at the given location
   *
   * @param location the position of the fire boots
   * @param individualID the individual id of the boots
   */
  public FireBoots(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new FireBoots(location, id);
  }
}
