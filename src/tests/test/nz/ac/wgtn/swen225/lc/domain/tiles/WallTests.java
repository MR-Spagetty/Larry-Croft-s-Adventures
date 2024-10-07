package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import nz.ac.wgtn.swen225.lc.domain.tiles.Wall;
import org.junit.jupiter.api.Test;

public class WallTests {
  @Test
  void put() {
    Wall t = w(0, 0);
    assertThrows(UOE, () -> t.put(e(0, 0)));
  }

  @Test
  void cantEnter() {
    Wall t = w(0, 0);
    assert !t.canEnter(e(0, 0));
  }

  @Test
  void enter() {
    Wall t = w(0, 0);
    assertThrows(UOE, () -> t.enter(e(0, 0)));
  }

  @Test
  void getEmpty() {
    Wall t = w(0, 0);
    assert t.getOccupant().isEmpty();
  }

  @Test
  void leave() {
    Wall t = w(0, 0);
    t.leave(e(0, 0));
  }
}
