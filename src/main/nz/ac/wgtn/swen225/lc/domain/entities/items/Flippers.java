package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Flippers allow the player to walk in water tiles without dying
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class Flippers extends Item {

  /**
   * creates a new set of flippers and the given position with the given individual id
   *
   * @param location the position to create the flippers at
   * @param individualID the individual id of the flippers
   */
  public Flippers(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new Flippers(location, id);
  }
}
