package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.IAE;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.area3x3;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.p;

import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;
import org.junit.jupiter.api.Test;
import test.nz.ac.wgtn.swen225.lc.domain.Shorthands;
import test.nz.ac.wgtn.swen225.lc.domain.entities.EntityBaseTests;
import test.nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntityBaseTests;

public interface EnemyBaseTests extends MoveableEntityBaseTests {
  default void runMany(Supplier<Enemy> toRun, int times, BiConsumer<Enemy, Enemy> check) {
    IntStream.range(0, times)
        .parallel()
        .mapToObj(i -> toRun.get())
        .reduce(
            (a, b) -> {
              check.accept(a, b);
              return b;
            });
  }

  @Test
  default void tickPastTick() {
    Maze scene = new Maze(40, " ", 0);
    area3x3(scene, Shorthands::et, 0, 0);
    scene.addEntity(new Player(p(1, 1), 0));
    Entity e = entity();
    scene.addEntity(e);
    e.tick(5);
    ((MoveableEntity) e).move(Point.ORIGIN.sub(e.location()));
    assertEquals(Point.ORIGIN, e.location());
    e.tick(2);
    assertEquals(Point.ORIGIN, e.location());
  }

  @Test
  default void unknownFromJSONEnemy() {
    assertThrows(IAE, () -> Enemy.fromJSON(EntityBaseTests.badData()));
  }
}
