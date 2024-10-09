package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.East;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class EConvTests implements ConveyorBaseTests {

  @Test
  public void fromEast() {
    Maze scenario = getScencario();
    Player p = getPlayer(East);
    scenario.addEntity(p);
    p.queueAction(Left);
    assertThrows(IAE, () -> p.tick(0));
  }

  @Override
  public Tile tile() {
    return tile(1);
  }

  @Override
  public void test(Point start, PlayerAction dir) {
    testFromBase(start, dir, East, Left, Right);
    testAngledBase(start, dir, East, Up, Down);
  }
}
