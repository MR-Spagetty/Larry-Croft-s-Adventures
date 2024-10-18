package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class DirIceNWTests implements DirectionalIceTests {

  @Override
  public Tile tile() {
    return tile(3);
  }

  @Test
  public void fromNorth() {
    testFromBase(North, Down, West);
  }

  @Test
  public void fromEast() {
    Maze scene = getScenario();
    Player player = getPlayer(East);
    scene.addEntity(player);
    player.queueAction(Left);
    assertThrows(IAE, () -> player.tick(0));
  }

  @Test
  public void fromSouth() {
    Maze scene = getScenario();
    Player player = getPlayer(South);
    scene.addEntity(player);
    player.queueAction(Up);
    assertThrows(IAE, () -> player.tick(0));
  }

  @Test
  public void fromWest() {
    testFromBase(West, Right, North);
  }

  @Test
  public void alreadyOccupiedEnter() {
    Player p = new Player(West, 0);
    Tile t = tile();
    new Maze(1, "NONE", 0, List.of(t, et(West)), List.of(e(Point.ORIGIN), p));
    assertThrows(IAE, () -> t.enter(p));
  }
}