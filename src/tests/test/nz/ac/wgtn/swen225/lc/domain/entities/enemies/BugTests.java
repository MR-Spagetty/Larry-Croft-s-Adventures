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
import org.junit.jupiter.api.Test;
import test.nz.ac.wgtn.swen225.lc.domain.Shorthands;
import test.nz.ac.wgtn.swen225.lc.domain.entities.EntityBaseTests;

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

  @Test
  void deserializedEqual() {
    Maze scene = new Maze(40, " ", 0);
    area3x3(scene, Shorthands::et, 0, 0);
    Entity e = entity();
    scene.addEntity(e);
    e.tick(0);
    Maze sceneRep = new Maze(40, " ", 0);
    area3x3(sceneRep, Shorthands::et, 0, 0);
    Entity e2 = Entity.fromJSON(e.toJson());
    sceneRep.addEntity(e2);
    assert e2 instanceof Bug;
    assertEquals(e.location(), e2.location());
    assertEquals(e.getUID(), e2.getUID());
    IntStream.range(1, 20)
        .forEach(
            i -> {
              e.tick(i);
              e2.tick(i);
              assertEquals(
                  e.location(),
                  e2.location(),
                  "Replicated and original differ at tick: %d by %s"
                      .formatted(i, e2.location().sub(e.location())));
            });
  }

  @Test
  public void badDeserializeData() {
    assertThrows(IAE, () -> Bug.fromJSON(EntityBaseTests.badData()));
  }
}
