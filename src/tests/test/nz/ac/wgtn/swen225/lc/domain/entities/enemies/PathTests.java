package test.nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static test.nz.ac.wgtn.swen225.lc.domain.Shorthands.*;

import java.util.List;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Path;
import org.junit.jupiter.api.Test;

public class PathTests {
  @Test
  void cons1() {
    Path p = new Path(Point.ORIGIN, Point.ORIGIN);
    assert p.prev().isEmpty();
    assertEquals(0, p.weight());
    assertEquals(0, p.totalWeight());
  }

  @Test
  void cons2() {
    Path p1 = new Path(Point.ORIGIN, Point.ORIGIN);
    Path p = new Path(p1, Point.ORIGIN, 1, Point.ORIGIN);
    assert p.prev().isPresent();
    assert p.prev().get() == p1;
    assertEquals(1, p.weight());
    assertEquals(2, p.totalWeight());
  }

  @Test
  void stream() {
    Path p1 = new Path(Point.ORIGIN, Point.ORIGIN);
    Path p = new Path(p1, Point.ORIGIN, 1, Point.ORIGIN);
    assertEquals(Stream.of(Point.ORIGIN, Point.ORIGIN).toList(), p.stream().toList());
  }

  @Test
  void toList() {
    Path p1 = new Path(Point.ORIGIN, Point.ORIGIN);
    Path p = new Path(p1, Point.ORIGIN, 1, Point.ORIGIN);
    assertEquals(List.of(Point.ORIGIN, Point.ORIGIN), p.toList());
  }

  @Test
  void minimalEstimation() {
    Path p = new Path(Point.ORIGIN, p(3, 4));
    assertEquals(5, p.estimatedWeight());
  }

  @Test
  void multiPartEstimated() {
    Path p1 = new Path(South, p(3, 4));
    Path p = new Path(p1, Point.ORIGIN, 1, p(3, 4));
    assertEquals(6, p.estimatedWeight());
  }

  @Test
  void comparitor(){
    assertEquals(-1, new Path(Point.ORIGIN, Point.ORIGIN).compareTo(new Path(South, Point.ORIGIN)));
    assertEquals(1, new Path(South, Point.ORIGIN).compareTo(new Path(Point.ORIGIN, Point.ORIGIN)));
  }
}
