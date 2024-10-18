package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.BitFlipper;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Bug;
import nz.ac.wgtn.swen225.lc.domain.tiles.Static;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public class StaticTests implements TileBaseTests {

  @Override
  public Tile tile() {
    return new Static(Point.ORIGIN);
  }

  @Test
  void mayNotEnter() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());
    Bug b = new Bug(Point.ORIGIN, 0);
    b.maze(scenario);
    BitFlipper bf = new BitFlipper(Point.ORIGIN, 0);
    bf.maze(scenario);
    assert !tile.canEnter(b);
    assert !tile.canEnter(bf);
  }

  @Test
  void putAlreadyOccupied() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());

    Bug b = new Bug(Point.ORIGIN, 0);
    b.maze(scenario);
    BitFlipper bf = new BitFlipper(Point.ORIGIN, 0);
    bf.maze(scenario);
    tile.put(b);
    assertThrows(ISE, () -> tile.put(bf));
  }

  @Test
  void enemysAttemptEnter() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());
    Bug b = new Bug(Point.ORIGIN, 0);
    b.maze(scenario);
    BitFlipper bf = new BitFlipper(Point.ORIGIN, 0);
    bf.maze(scenario);
    assertThrows(IAE, () -> tile.enter(b));
    assertThrows(IAE, () -> tile.enter(bf));
  }

  @Test
  void playerMayEnter() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scenario);
    assert tile.canEnter(p);
  }

  @Test
  void playerMayPut() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scenario);
    tile.put(p);
    assert tile.getOccupant().isPresent();
    assert tile.getOccupant().get() == p;
  }

  @Test
  void playerEnter() {
    Tile tile = tile();
    Maze scenario = new Maze(0, " ", 0, List.of(tile), List.of());
    Player p = new Player(Point.ORIGIN, 0);
    p.maze(scenario);
    tile.enter(p);
    assert tile.getOccupant().isPresent();
    assert tile.getOccupant().get() == p;
  }
}
