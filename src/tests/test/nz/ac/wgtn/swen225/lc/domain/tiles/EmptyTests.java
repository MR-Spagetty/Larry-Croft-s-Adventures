package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public class EmptyTests implements TileBaseTests {

  @Override
  public Tile tile() {
    return et(0, 0);
  }

  @Test
  void finePut() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
  }

  @Test
  void badPut() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    assertThrows(ISE, () -> t.put(e));
    Entity e2 = e(0, 0);
    e2.maze(scenario);
    assertThrows(ISE, () -> t.put(e2));
  }

  @Test
  void getEmpty() {
    Tile t = tile();
    assert t.getOccupant().isEmpty();
  }

  @Test
  void getOccupied() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    assert t.getOccupant().isPresent();
    assertEquals(t.getOccupant().get(), e);
  }

  @Test
  void canEnter() {
    Tile t = tile();
    assert t.canEnter(e(0, 0));
  }

  @Test
  void cantEnter() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    assert !t.canEnter(e);
    Entity e2 = e(0, 0);
    e2.maze(scenario);
    assert !t.canEnter(e2);
  }

  @Test
  void enterGood() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.enter(e);
    assertEquals(e, t.getOccupant().get());
  }

  @Test
  void enterBad() {
    Tile t = et(0, 0);
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    Entity e2 = e(0, 0);
    e2.maze(scenario);
    assertThrows(IAE, () -> t.enter(e2));
  }

  @Test
  void leave1() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    t.leave(e);
    assert t.getOccupant().isEmpty();
  }

  @Test
  void leave2() {
    Tile t = tile();
    Maze scenario = new Maze(0l, "NONE", 0, List.of(t), List.of());
    Entity e = e(0, 0);
    e.maze(scenario);
    t.put(e);
    t.leave(e(0, 0));
    assert t.getOccupant().isPresent();
  }
}
