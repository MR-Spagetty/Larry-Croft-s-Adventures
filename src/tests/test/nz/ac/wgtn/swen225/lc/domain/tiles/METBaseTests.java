package test.nz.ac.wgtn.swen225.lc.domain.tiles;

import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.et;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.p;

import java.util.stream.IntStream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

public interface METBaseTests extends TileBaseTests {
  default Player getPlayer(long x, long y) {
    return getPlayer(p(x, y));
  }

  default Player getPlayer(Point p) {
    return new Player(p, 0);
  }

  default Maze getScenario() {
    Maze out = new Maze(10000l, "NONE", 0);
    IntStream.range(-1, 2).mapToObj(x -> et(x, -1)).forEach(out::addTile);
    out.addTile(et(-1, 0));
    out.addTile(tile());
    out.addTile(et(1, 0));
    IntStream.range(-1, 2).mapToObj(x -> et(x, 1)).forEach(out::addTile);
    return out;
  }

  void fromNorth();

  void fromSouth();

  void fromEast();

  void fromWest();

  void fromOn();
}
