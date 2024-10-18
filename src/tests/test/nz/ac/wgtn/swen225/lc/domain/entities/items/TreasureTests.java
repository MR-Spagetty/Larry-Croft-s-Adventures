package test.nz.ac.wgtn.swen225.lc.domain.entities.items;


import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Treasure;

public class TreasureTests implements ItemBaseTests {

  @Override
  public Entity entity() {
    return new Treasure(Point.ORIGIN, 0);
  }
}
