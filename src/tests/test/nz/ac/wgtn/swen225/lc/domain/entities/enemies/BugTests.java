package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.stream.IntStream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Bug;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;
import org.junit.jupiter.api.Test;
import test.nz.ac.wgtn.swen225.lc.domain.Shorthands;

public class BugTests implements EnemyBaseTests {

  @Override
  public Entity entity() {
    return new Bug(Point.ORIGIN, 0);
  }

  @Test
  void idenFirstMove() {
    IntStream.range(0, 20)
        .forEach(
            i -> {
              runMany(
                  () -> {
                    Maze scene = new Maze(i, " ", 0);
                    Entity e = entity();
                    area3x3(scene, Shorthands::et, 0, 0);
                    scene.addEntity(e);
                    IntStream.range(0, i).forEach(e::tick);
                    return (Enemy) e;
                  },
                  20,
                  (a, b) -> assertEquals(a.location(), b.location()));
            });
  }
}
