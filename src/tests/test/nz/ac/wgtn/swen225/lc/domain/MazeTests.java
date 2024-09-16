package test.nz.ac.wgtn.swen225.lc.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.Tile;
import org.junit.jupiter.api.Test;

public class MazeTests {
  Class<IllegalArgumentException> IAE = IllegalArgumentException.class;

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
    new Maze(0);
    new Maze(10);
  }

  @Test
  void badEmptyInit() {
    assertThrows(IAE, () -> new Maze(-1));
    assertThrows(IAE, () -> new Maze(-10));
  }

  @Test
  void goodTileOnly1() {
    Maze maze = new Maze(0);
    area3x3(maze, Shorthands::et, 0, 0);
  }

  @Test
  void badTileOnly() {
    Maze maze = new Maze(0);
    area3x3(maze, Shorthands::et, 0, 0);
    area3x3(Shorthands::et, 0, 0).forEach(t -> assertThrows(IAE, () -> maze.addTile(t)));
  }

  @Test
  void goodEntity() {
    Maze maze = new Maze(0);
    maze.addTile(et(0, 0));
    maze.addEntity(e(0, 0));
  }

  @Test
  void badEntity() {
    Maze maze = new Maze(0);
    assertThrows(IAE, () -> maze.addEntity(e(0, 0)));
  }

  @Test
  void goodFullConst1() {
    Maze maze = new Maze(0, area3x3(Shorthands::et, 0, 0), List.of());
    area3x3(Shorthands::et, 0, 0).forEach(t -> assertThrows(IAE, () -> maze.addTile(t)));
  }

  @Test
  void goodFullConst2() {
    Maze maze = new Maze(0, List.of(et(0, 0)), List.of(e(0, 0)));
  }

  @Test
  void badFullConst1() {
    assertThrows(IAE, () -> new Maze(0, List.of(et(0, 0), et(0, 0)), List.of()));
  }

  @Test
  void badFullConst2() {
    assertThrows(IAE, () -> new Maze(0, List.of(), List.of(e(0, 0))));
  }
}
