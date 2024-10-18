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

public final class DirIceNETests implements DirectionalIceTests {

  @Override
  public Tile tile() {
    return tile(0);
  }

  @Test
  public void fromNorth() {
    testFromBase(North, Down, East);
  }

  @Test
  public void fromEast() {
    testFromBase(East, Left, North);
  }

  @Test
  public void fromSouth() {
    Maze scene = getScenario();
    Player player = getPlayer(South);
    scene.addEntity(player);
    player.queueAction(Up);
    Point before = player.location();
    player.tick(0);
    assertEquals(before, player.location());
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
    Player p = new Player(East, 0);
    Tile t = tile();
    new Maze(1, "NONE", 0, List.of(t, et(East)), List.of(e(Point.ORIGIN), p));
    assertThrows(IAE, () -> t.enter(p));
  }
}