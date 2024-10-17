package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import java.util.stream.IntStream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Bug;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;
import nz.ac.wgtn.swen225.lc.domain.entities.items.IceBoots;
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

  @Test
  void hitWall() {
    Maze scene =
        new Maze(50, " ", 0, List.of(w(1, 0), w(0, 1), w(-1, 0), w(0, -1), et(0, 0)), List.of());
    Entity e = entity();
    scene.addEntity(e);
    IntStream.range(0, 50).forEach(e::tick);
  }

  @Test
  void playerTouch() {
    Player p = new Player(East, 0);
    Entity e = entity();
    assert !p.isDead();
    e.touch(p);
    assert p.isDead();
  }

  @Test
  void nonPlayerTouch() {
    Maze scene = new Maze(0, " ", 0);
    Entity e = entity();
    e.maze(scene);
    assertThrows(IAE, () -> e.touch(e));
  }
}
