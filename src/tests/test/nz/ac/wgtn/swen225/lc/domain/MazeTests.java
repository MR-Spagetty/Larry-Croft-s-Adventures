package test.nz.ac.wgtn.swen225.lc.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import org.junit.jupiter.api.Test;

public class MazeTests {

  List<Tile> area3x3(Function<Point, Tile> tile, long cX, long cY) {
    Point cPoint = new Point(cX, cY);
    return Stream.<Long>of(-1l, 0l, 1l)
        .<Tile>mapMulti(
            (x, cons) ->
                Stream.<Long>of(-1l, 0l, 1l)
                    .forEach(y -> cons.accept(tile.apply(cPoint.add(p(x, y))))))
        .toList();
  }

  void area3x3(Maze m, Function<Point, Tile> tile, long cX, long cY) {
    area3x3(tile, cX, cY).forEach(m::addTile);
  }

  @Test
  void goodEmptyInit() {
    new Maze(0, "", 0);
    new Maze(10, "", 0);
  }

  @Test
  void badEmptyInit() {
    assertThrows(IAE, () -> new Maze(-1, "", 0));
    assertThrows(IAE, () -> new Maze(-10, "", 0));
  }

  @Test
  void goodTileOnly1() {
    Maze maze = new Maze(0, "", 0);
    area3x3(maze, Shorthands::et, 0, 0);
  }

  @Test
  void badTileOnly() {
    Maze maze = new Maze(0, "", 0);
    area3x3(maze, Shorthands::et, 0, 0);
    area3x3(Shorthands::et, 0, 0).forEach(t -> assertThrows(IAE, () -> maze.addTile(t)));
  }

  @Test
  void goodEntity() {
    Maze maze = new Maze(0, "", 0);
    maze.addTile(et(0, 0));
    maze.addEntity(e(0, 0));
  }

  @Test
  void badEntity() {
    Maze maze = new Maze(0, "", 0);
    assertThrows(IAE, () -> maze.addEntity(e(0, 0)));
  }

  @Test
  void goodFullConst1() {
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), List.of());
    area3x3(Shorthands::et, 0, 0).forEach(t -> assertThrows(IAE, () -> maze.addTile(t)));
  }

  @Test
  void goodFullConst2() {
    new Maze(0, "", 0, List.of(et(0, 0)), List.of(e(0, 0)));
  }

  @Test
  void badFullConst1() {
    assertThrows(IAE, () -> new Maze(0, "", 0, List.of(et(0, 0), et(0, 0)), List.of()));
  }

  @Test
  void badFullConst2() {
    assertThrows(IAE, () -> new Maze(0, "", 0, List.of(), List.of(e(0, 0))));
  }

  @Test
  void getTile1() {
    Tile exp = et(0, 0);
    Maze maze = new Maze(0, "", 0, List.of(exp), List.of());
    Tile found = maze.getTile(p(0, 0)).get();
    assertEquals(exp, found);
    assert exp == found;
  }

  @Test
  void getTile2() {
    Maze maze = new Maze(0, "", 0);
    Optional<Tile> found = maze.getTile(p(0, 0));
    assert found.isEmpty();
  }

  @Test
  void getTiles1() {
    List<Tile> exp = area3x3(Shorthands::et, 0, 0);
    Maze maze = new Maze(0, "", 0, exp, List.of());
    assertEquals(exp, maze.getTiles(p(0, 0), 1));
  }

  @Test
  void getTiles2() {
    List<Tile> in = area3x3(Shorthands::et, 0, 0);
    Tile exp = in.get(4);
    Maze maze = new Maze(0, "", 0, in, List.of());
    List<Tile> found = maze.getTiles(p(0, 0), 0);
    assertEquals(1, found.size());
    assertEquals(exp, found.getFirst());
    assertEquals(maze.getTile(p(0, 0)).get(), found.getFirst());
  }

  @Test
  void getTiles3() {
    List<Tile> in = area3x3(Shorthands::et, 0, 0);
    Tile exp = in.get(0);
    Maze maze = new Maze(0, "", 0, in, List.of());
    assertEquals(exp, maze.getTile(p(-1, -1)).get());
    List<Tile> found = maze.getTiles(p(-2, -2), 1);
    assertEquals(1, found.size());
    assertEquals(exp, found.getFirst());
  }

  @Test
  void getTiles4() {
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), List.of());
    assertEquals(0, maze.getTiles(p(-3, 0), 1).size());
  }

  @Test
  void getEntity1() {
    Entity exp = e(0, 0);
    Maze maze = new Maze(0, "", 0, List.of(et(0, 0)), List.of(exp));
    Entity found = maze.getEntity(p(0, 0)).get();
    assertEquals(exp, found);
    assert exp == found;
  }

  @Test
  void getEntity2() {
    Maze maze = new Maze(0, "", 0);
    Optional<Entity> found = maze.getEntity(p(0, 0));
    assert found.isEmpty();
  }

  @Test
  void getEntity3() {
    Maze maze = new Maze(0, "", 0, List.of(et(0, 0)), List.of());
    Optional<Entity> found = maze.getEntity(p(0, 0));
    assert found.isEmpty();
  }

  @Test
  void getAllEntities() {
    Entity exp = e(0, 0);
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), List.of(exp));
    List<Entity> out = maze.getEntities();
    assertEquals(1, out.size());
    assertEquals(exp, out.getFirst());
    assert exp == out.getFirst();
    assertThrows(UOE, () -> out.clear());
  }

  @Test
  void getEntities1() {
    List<Entity> expected =
        area3x3(Shorthands::et, 0, 0).stream().map(t -> e(t.location())).toList();
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), expected);
    List<Entity> out = maze.getEntities(p(0, 0), 1);
    assertEquals(expected, out);
  }

  @Test
  void getEntities2() {
    List<Entity> expected =
        area3x3(Shorthands::et, 0, 0).stream().map(t -> e(t.location())).toList();
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), expected);
    List<Entity> out = maze.getEntities(p(0, 0), 1);
    assertEquals(expected, out);
  }

  @Test
  void getEntities3() {
    List<Entity> in = area3x3(Shorthands::et, 0, 0).stream().map(t -> e(t.location())).toList();
    Entity expected = in.get(4);
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), in);
    List<Entity> out = maze.getEntities(p(0, 0), 0);
    assertEquals(1, out.size());
    assertEquals(expected, out.getFirst());
    assert expected == out.getFirst();
  }

  @Test
  void getEntities4() {
    List<Entity> in = area3x3(Shorthands::et, 0, 0).stream().map(t -> e(t.location())).toList();
    Entity expected = in.get(0);
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 0, 0), in);
    List<Entity> out = maze.getEntities(p(-2, -2), 1);
    assertEquals(1, out.size());
    assertEquals(expected, out.getFirst());
    assert expected == out.getFirst();
  }

  @Test
  void getEntities5() {
    Maze maze = new Maze(0, "", 0, area3x3(Shorthands::et, 1, 0), List.of(e(p(0, 0))));
    assertEquals(0, maze.getEntities(p(2, 0), 1).size());
  }
}
