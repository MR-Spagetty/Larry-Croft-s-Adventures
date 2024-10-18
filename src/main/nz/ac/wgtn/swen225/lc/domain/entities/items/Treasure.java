package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Treasures are the item that needs to be collected in all levels to open the exit door to have
 * access to teh exit
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public class Treasure extends Item {

  /**
   * Creates a new Treasure item at the given position with the given individualID
   *
   * @param location the position of the treasure
   * @param individualIDthe individual ID of the item
   */
  public Treasure(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new Treasure(location, id);
  }
}
