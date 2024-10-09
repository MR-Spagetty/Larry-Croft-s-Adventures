package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.West;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class WConvTests implements ConveyorBaseTests {

  @Test
  public void fromOn() {}

  @Test
  public void fromWest() {
    Maze scenario = getScencario();
    Player p = getPlayer(West);
    scenario.addEntity(p);
    p.queueAction(Left);
    assertThrows(IAE, () -> p.tick(0));
  }

  @Override
  public Tile tile() {
    return tile(3);
  }

  @Override
  public void test(Point start, PlayerAction dir) {
    testFromBase(start, dir, West, Left, Right);
    testAngledBase(start, dir, West, Up, Down);
  }
}
