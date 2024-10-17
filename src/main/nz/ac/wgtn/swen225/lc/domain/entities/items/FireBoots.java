package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

public class FireBoots extends Item {

  public FireBoots(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new FireBoots(location, id);
  }
}
