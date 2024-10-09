package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.p;

import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Ice;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public class IceTests implements IceBaseTests {

  @Override
  public Tile tile() {
    return new Ice(Point.ORIGIN);
  }

  @Test
  void fromNorth() {
    testFromBase(p(0, 1), Down, p(0, -1));
  }

  @Test
  void fromSouth() {
    testFromBase(p(0, -1), Up, p(0, 1));
  }

  @Test
  void fromEast() {
    testFromBase(p(1, 0), Left, p(-1, 0));
  }

  @Test
  void fromWest() {
    testFromBase(p(-1, 0), Right, p(1, 0));
  }

  @Test
  void fromOn() {
    Stream.of(PlayerAction.values())
        .forEach(
            act -> {
              Maze scenario = getScencario();
              Player player = getPlayer(Point.ORIGIN);
              scenario.addEntity(player);
              player.queueAction(act);
              player.tick(0);
              assertEquals(act.offset, player.location());
            });
  }
}
