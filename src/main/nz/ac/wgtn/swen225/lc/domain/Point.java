package nz.ac.wgtn.swen225.lc.domain;

/**
 * Represents a point in a 2D space using long coordinates.
 *
 * @param x the x-coordinate of the point
 * @param y the y-coordinate of the point
 */
public record Point(long x, long y) implements Comparable<Point> {

  /**
   * Adds the coordinates of this point with another point.
   *
   * @param other the point to add
   * @return a new Point with the sum of the coordinates
   */
  public Point add(Point other) {
    return new Point(this.x + other.x, this.y + other.y);
  }

  /**
   * Subtracts the coordinates of another point from this point.
   *
   * @param other the point to subtract
   * @return a new Point with the difference of the coordinates
   */
  public Point sub(Point other) {
    return new Point(this.x - other.x, this.y - other.y);
  }

  /**
   * Calculates the Euclidean distance between this point and another point.
   *
   * @param other the point to calculate the distance to
   * @return the Euclidean distance between the two points
   */
  public Double dist(Point other) {
    Point diff = sub(other);
    return Math.sqrt(diff.x * diff.x + diff.y * diff.y);
  }

  /**
 * Compares this point with another point for order.
 *
 * defines a natural ordering for points.
 * The ordering is first based on the x-coordinate, and then on the y-coordinate.
 *
 * @param other the point to be compared with this point
 * @return an integer representing teh ordering of the points
 */
@Override
public int compareTo(Point other) {
  int xComp = Long.compare(this.x, other.x);
  return xComp != 0 ? xComp : Long.compare(this.y, other.y);
}
}

