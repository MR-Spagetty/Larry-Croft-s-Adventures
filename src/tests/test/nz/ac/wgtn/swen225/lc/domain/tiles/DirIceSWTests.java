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

public final class DirIceSWTests implements DirectionalIceTests {

  @Override
  public Tile tile() {
    return tile(2);
  }

  @Test
  public void fromNorth() {
    Maze scene = getScencario();
    Player player = getPlayer(North);
    scene.addEntity(player);
    player.queueAction(Down);
    assertThrows(IAE, () -> player.tick(0));
  }

  @Test
  public void fromSouth() {
    testFromBase(South, Up, West);
  }

  @Test
  public void fromEast() {
    Maze scene = getScencario();
    Player player = getPlayer(East);
    scene.addEntity(player);
    player.queueAction(Left);
    assertThrows(IAE, () -> player.tick(0));
  }

  @Test
  public void fromWest() {
    testFromBase(West, Right, South);
  }

  @Test
  public void fromOn() {}

  @Test
  public void alreadyOccupiedEnter() {
    Player p = new Player(West, 0);
    Tile t = tile();
    new Maze(1, "NONE", List.of(t, et(West)), List.of(e(Point.ORIGIN), p));
    assertThrows(ISE, () -> t.enter(p));
  }
}