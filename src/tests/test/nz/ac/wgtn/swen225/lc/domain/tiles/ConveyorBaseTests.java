package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Conveyor;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

public sealed interface ConveyorBaseTests extends METBaseTests
    permits NConvTests, EConvTests, SConvTests, WConvTests {
  default Tile tile(int dir) {
    Conveyor t = new Conveyor(Point.ORIGIN, dir);
    assertEquals( Conveyor.DIRS[dir], t.getFacing());
    return t;
  }

  default void testFromBase(Point start, PlayerAction dir, Point end, PlayerAction... nullActs) {
    Stream.concat(Stream.of(None), Stream.of(nullActs))
        .forEach(
            act -> {
              Maze scenario = getScencario();
              Player player = getPlayer(start);
              scenario.addEntity(player);
              player.queueAction(dir);
              player.tick(0);
              player.queueAction(act);
              player.tick(1);
              assertEquals(end, player.location());
            });
  }

  default void testAngledBase(
      Point start, PlayerAction dir, Point endNoAction, PlayerAction... actions) {
    Stream.concat(Stream.of(None), Stream.of(actions))
        .forEach(
            act -> {
              Maze scenario = getScencario();
              Player player = getPlayer(start);
              scenario.addEntity(player);
              player.queueAction(dir);
              player.tick(0);
              player.queueAction(act);
              player.tick(1);
              assertEquals(endNoAction.add(act.offset), player.location());
            });
  }

  void test(Point start, PlayerAction dir);

  @Test
  default void fromNorth() {
    test(North, Down);
  }

  @Test
  default void fromSouth() {
    test(South, Up);
  }

  @Test
  default void fromEast() {
    test(East, Left);
  }

  @Test
  default void fromWest() {
    test(West, Right);
  }
}
