package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Ice;
import nz.ac.wgtn.swen225.lc.domain.tiles.AbstractTile;
import org.junit.jupiter.api.Test;

public class IceTests implements IceBaseTests {

  @Override
  public AbstractTile tile() {
    return new Ice(Point.ORIGIN);
  }

  @Test
  public void fromNorth() {
    testFromBase(North, Down, South);
  }

  @Test
  public void fromSouth() {
    testFromBase(South, Up, North);
  }

  @Test
  public void fromEast() {
    testFromBase(East, Left, West);
  }

  @Test
  public void fromWest() {
    testFromBase(West, Right, East);
  }

  @Test
  public void fromOn() {
    Stream.of(PlayerAction.values())
        .forEach(
            act -> {
              Maze scenario = getScenario();
              Player player = getPlayer(Point.ORIGIN);
              scenario.addEntity(player);
              player.queueAction(act);
              player.tick(0);
              assertEquals(act.offset, player.location());
            });
  }
}
