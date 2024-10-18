package test.nz.ac.wgtn.swen225.lc.domain.entities.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import nz.ac.wgtn.swen225.lc.domain.Colour;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Key;
import org.junit.jupiter.api.Test;

public class KeyTests implements ItemBaseTests {

  @Override
  public Entity entity() {
    return new Key(Point.ORIGIN, 0, new Colour(Color.black));
  }

  @Test
  public void serialEqual() {
    Maze scene = new Maze(0, " ", 0);
    Entity a = entity();
    a.maze(scene);
    Entity b = ((Item) entity()).fromJson(a.toJson());
    b.maze(scene);
    assertEquals(a.location(), b.location());
    assertEquals(a.getUID(), b.getUID());
    assertEquals(((Key) a).colour, ((Key) b).colour);
  }

  @Test
  public void getColour() {
    assertEquals(java.awt.Color.black, ((Key) entity()).color());
  }
}
