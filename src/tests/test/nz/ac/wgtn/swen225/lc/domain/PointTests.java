package test.nz.ac.wgtn.swen225.lc.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nz.ac.wgtn.swen225.lc.domain.Point;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Point class.
 *
 * <p>This class contains tests for the add, subtract, distance, and comparison methods of the Point
 * record. The tests cover various scenarios, including positive, negative, and mixed coordinates
 * where required.
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class PointTests {
  @Test
  void addZero() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Point result = p1.add(p2);
    assertEquals(result, new Point(0, 0));
  }

  @Test
  void addBothPositive() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    Point result = p1.add(p2);
    assertEquals(result, new Point(4, 6));
  }

  @Test
  void addBothNegative() {
    Point p1 = new Point(-1, -2);
    Point p2 = new Point(-3, -4);
    Point result = p1.add(p2);
    assertEquals(result, new Point(-4, -6));
  }

  @Test
  void addMixed() {
    Point p1 = new Point(1, -2);
    Point p2 = new Point(-3, 4);
    Point result = p1.add(p2);
    assertEquals(result, new Point(-2, 2));
  }

  @Test
  void subtractZero() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Point result = p1.sub(p2);
    assertEquals(result, new Point(0, 0));
  }

  @Test
  void subtractBothPositive() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    Point result = p1.sub(p2);
    assertEquals(result, new Point(-2, -2));
  }

  @Test
  void subtractBothNegative() {
    Point p1 = new Point(-1, -2);
    Point p2 = new Point(-3, -4);
    Point result = p1.sub(p2);
    assertEquals(result, new Point(2, 2));
  }

  @Test
  void subtractMixed() {
    Point p1 = new Point(1, -2);
    Point p2 = new Point(-3, 4);
    Point result = p1.sub(p2);
    assertEquals(result, new Point(4, -6));
  }

  @Test
  void distance0() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    double result = p1.dist(p2);
    assertEquals(result, 0.0);
  }

  @Test
  void distance1() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    double result = p1.dist(p2);
    assertEquals(result, 5.0);
  }

  @Test
  void distance2() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(-3, -4);
    double result = p1.dist(p2);
    assertEquals(result, 5.0);
  }

  @Test
  void distance3() {
    Point p1 = new Point(3, 4);
    Point p2 = new Point(6, 8);
    double result = p1.dist(p2);
    assertEquals(result, 5.0);
  }

  @Test
  void distance4() {
    Point p1 = new Point(-3, -4);
    Point p2 = new Point(-6, -8);
    double result = p1.dist(p2);
    assertEquals(result, 5.0);
  }

  @Test
  void distance5() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(-2, -2);
    double result = p1.dist(p2);
    assertEquals(result, 5.0);
  }

  @Test
  void compareDifferentX1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 2);
    assertEquals(p1.compareTo(p2), -1);
  }

  @Test
  void compareDifferentX2() {
    Point p1 = new Point(3, 2);
    Point p2 = new Point(1, 2);
    assertEquals(p1.compareTo(p2), 1);
  }

  @Test
  void compareDifferentY1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 4);
    assertEquals(p1.compareTo(p2), -1);
  }

  @Test
  void compareDifferentY2() {
    Point p1 = new Point(1, 4);
    Point p2 = new Point(1, 2);
    assertEquals(p1.compareTo(p2), 1);
  }

  @Test
  void compareSame() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(1, 2);
    assertEquals(p1.compareTo(p2), 0);
  }
}
