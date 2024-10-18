package test.nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.items.IceBoots;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import test.nz.ac.wgtn.swen225.lc.domain.entities.EntityBaseTests;

public class IceBootsTests implements ItemBaseTests {

  @Override
  public Entity entity() {
    return new IceBoots(Point.ORIGIN, 0);
  }

  @Override
  public void badDeserializeData() {
    ((Item) entity()).fromJson(EntityBaseTests.badData());
  }
}