package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Empty;
import nz.ac.wgtn.swen225.lc.domain.tiles.AbstractTile;
import org.junit.jupiter.api.Test;

public class EmptyTests implements TileBaseTests {

  @Override
  public AbstractTile tile() {
  return et(0, 0);
  }

  @Test
  void finePut() {
    AbstractTile t = tile();
    Entity e = e(0, 0);
    t.put(e);
  }

  @Test
  void badPut() {
    AbstractTile t = tile();
    t.put(e(0, 0));
    assertThrows(ISE, () -> t.put(e(0, 0)));
  }

  @Test
  void getEmpty() {
    AbstractTile t = tile();
    assert t.getOccupant().isEmpty();
  }

  @Test
  void getOccupied() {
    Entity e = e(0, 0);
    AbstractTile t = tile();
    t.put(e);
    assert t.getOccupant().isPresent();
    assertEquals(t.getOccupant().get(), e);
  }

  @Test
  void canEnter() {
    AbstractTile t = tile();
    assert t.canEnter(e(0, 0));
  }

  @Test
  void cantEnter() {
    AbstractTile t = tile();
    t.put(e(0, 0));
    assert !t.canEnter(e(0, 0));
  }

  @Test
  void enterGood() {
    AbstractTile t = tile();
    Entity e = e(0, 0);
    t.enter(e);
    assertEquals(e, t.getOccupant().get());
  }

  @Test
  void enterBad() {
    AbstractTile t = et(0, 0);
    t.put(e(0, 0));
    assertThrows(ISE, () -> t.enter(e(0, 0)));
  }

  @Test
  void leave1() {
    AbstractTile t = tile();
    Entity e = e(0, 0);
    t.put(e);
    t.leave(e);
    assert t.getOccupant().isEmpty();
  }

  @Test
  void leave2() {
    AbstractTile t = tile();
    Entity e = e(0, 0);
    t.put(e);
    t.leave(e(0, 0));
    assert t.getOccupant().isPresent();
  }
}
