package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;

public class Treasure extends Item {

  public Treasure(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  public void tick(long tick) {}

  @Override
  protected Item item(Point location, long id) {
    return new Treasure(location, id);
  }
}
