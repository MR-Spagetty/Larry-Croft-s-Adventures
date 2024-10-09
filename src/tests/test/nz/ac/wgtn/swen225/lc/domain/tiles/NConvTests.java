package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.North;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class NConvTests implements ConveyorBaseTests {

  @Override
  public void test(Point start, PlayerAction dir) {
    testFromBase(start, dir, North, Up, Down);
    testAngledBase(start, dir, North, Left, Right);
  }

  @Test
  public void fromNorth() {
    Maze scenario = getScencario();
    Player p = getPlayer(North);
    scenario.addEntity(p);
    p.queueAction(Down);
    assertThrows(IAE, () -> p.tick(0));
  }

  @Test
  public void fromOn() {}

  @Override
  public Tile tile() {
    return tile(0);
  }
}
