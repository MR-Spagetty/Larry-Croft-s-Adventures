package test.nz.ac.wgtn.swen225.lc.domain.entities.items;

import java.awt.Color;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Key;
import test.nz.ac.wgtn.swen225.lc.domain.entities.EntityBaseTests;

public class KeyTests implements ItemBaseTests {

  @Override
  public Entity entity() {
    return new Key(Point.ORIGIN, 0, new Colour(Color.black));
  }

  @Override
  public void badDeserializeData() {
    ((Item) entity()).fromJson(EntityBaseTests.badData());
  }
}
