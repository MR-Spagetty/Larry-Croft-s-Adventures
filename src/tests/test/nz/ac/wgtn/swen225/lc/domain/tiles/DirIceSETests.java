package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public final class DirIceSETests implements DirectionalIceTests {

  @Override
  public Tile tile() {
    return tile(1);
  }

  @Test
  public void fromNorth() {
    Maze scene = getScenario();
    Player player = getPlayer(North);
    scene.addEntity(player);
    player.queueAction(Down);
    Point before = player.location();
    player.tick(0);
    assertEquals(before, player.location());
  }

  @Test
  public void fromSouth() {
    testFromBase(South, Up, East);
  }

  @Test
  public void fromEast() {
    testFromBase(East, Left, South);
  }

  @Test
  public void fromWest() {
    Maze scene = getScenario();
    Player player = getPlayer(West);
    scene.addEntity(player);
    player.queueAction(Right);
    Point before = player.location();
    player.tick(0);
    assertEquals(before, player.location());
  }

  @Test
  public void alreadyOccupiedEnter() {
    Player p = new Player(West, 0);
    Tile t = tile();
    new Maze(1, "NONE", 0, List.of(t, et(West)), List.of(e(Point.ORIGIN), p));
    assertThrows(IAE, () -> t.enter(p));
  }
}