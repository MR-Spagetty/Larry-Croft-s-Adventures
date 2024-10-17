package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

public class Flippers extends Item {

  public Flippers(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected Item item(Point location, long id) {
    return new Flippers(location, id);
  }
}
