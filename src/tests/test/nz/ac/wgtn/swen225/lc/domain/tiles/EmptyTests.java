package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Empty;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public class EmptyTests {

  @Test
  void finePut() {
    Empty t = (Empty) et(0, 0);
    Entity e = e(0, 0);
    t.put(e);
  }

  @Test
  void badPut() {
    Empty t = (Empty) et(0, 0);
    t.put(e(0, 0));
    assertThrows(ISE, () -> t.put(e(0, 0)));
  }

  @Test
  void getEmpty() {
    Empty t = (Empty) et(0, 0);
    assert t.getOccupant().isEmpty();
  }

  @Test
  void getOccupied() {
    Entity e = e(0, 0);
    Empty t = (Empty) et(0, 0);
    t.put(e);
    assert t.getOccupant().isPresent();
    assertEquals(t.getOccupant().get(), e);
  }

  @Test
  void canEnter() {
    Empty t = (Empty) et(0, 0);
    assert t.canEnter(e(0, 0));
  }

  @Test
  void cantEnter() {
    Empty t = (Empty) et(0, 0);
    t.put(e(0, 0));
    assert !t.canEnter(e(0, 0));
  }

  @Test
  void enterGood() {
    Empty t = (Empty) et(0, 0);
    Entity e = e(0, 0);
    t.enter(e);
    assertEquals(e, t.getOccupant().get());
  }

  @Test
  void enterBad() {
    Tile t = et(0, 0);
    t.put(e(0, 0));
    assertThrows(ISE, () -> t.enter(e(0, 0)));
  }

  @Test
  void leave1() {
    Empty t = (Empty) et(0, 0);
    Entity e = e(0, 0);
    t.put(e);
    t.leave(e);
    assert t.getOccupant().isEmpty();
  }

  @Test
  void leave2() {
    Empty t = (Empty) et(0, 0);
    Entity e = e(0, 0);
    t.put(e);
    t.leave(e(0, 0));
    assert t.getOccupant().isPresent();
  }
}
