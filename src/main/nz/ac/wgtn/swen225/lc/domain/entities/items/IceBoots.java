package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

public class IceBoots extends Item {

  public IceBoots(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new IceBoots(location, id);
  }
}
