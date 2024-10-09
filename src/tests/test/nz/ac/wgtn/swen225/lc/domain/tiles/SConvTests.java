package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.South;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class SConvTests implements ConveyorBaseTests {
  @Override
  public void test(Point start, PlayerAction dir) {
    testFromBase(start, dir, South, Up, Down);
    testAngledBase(start, dir, South, Left, Right);
  }

  @Test
  public void fromSouth() {
    Maze scenario = getScencario();
    Player p = getPlayer(South);
    scenario.addEntity(p);
    p.queueAction(Up);
    assertThrows(IAE, () -> p.tick(0));
  }

  @Override
  public Tile tile() {
    return tile(2);
  }
}
