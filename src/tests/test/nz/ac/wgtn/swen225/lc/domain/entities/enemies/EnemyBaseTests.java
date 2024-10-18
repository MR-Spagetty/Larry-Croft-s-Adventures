package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;


import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;
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
}
