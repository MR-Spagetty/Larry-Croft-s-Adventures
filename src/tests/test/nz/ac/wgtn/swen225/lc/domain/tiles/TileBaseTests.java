package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.Point.ORIGIN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public interface TileBaseTests {
  Tile tile();

  @Test
  default void goodConst() {
    tile();
  }

  @Test
  default void leaveNonCurrentTile() {
    Player p = new Player(West, 0);
    Tile t = tile();
    new Maze(1, "NONE", List.of(t, et(West)), List.of(e(ORIGIN), p));
    t.leave(p);
  }

  @Test
  default void alreadyOccupiedPut() {
    assertThrows(
        ISE,
        () -> {
          new Maze(0, "NONE", List.of(tile()), List.of(e(ORIGIN), e(ORIGIN)));
        });
  }

  @Test
  default void alreadyOccupiedEnter() {
    Player p = new Player(West, 0);
    Tile t = tile();
    new Maze(1, "NONE", List.of(t, et(West)), List.of(e(ORIGIN), p));
    assertThrows(ISE, () -> t.enter(p));
  }
}
