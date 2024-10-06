package nz.ac.wgtn.swen225.lc.domain;

/**
 * Represents a point in a 2D space using long coordinates.
 *
 * @param x the x-coordinate of the point
 * @param y the y-coordinate of the point
 */
public record Point(long x, long y) implements Comparable<Point> {

  /**
   * Limits the coordinates of this point within a specified maximum axial value.
   *
   * <p>The method ensures that the x and y coordinates of the point fall within the range of
   * -maxAxial to maxAxial (inclusive). If a coordinate exceeds the maximum or falls below the
   * minimum, it is adjusted to the nearest boundary value.
   *
   * @param maxAxial the maximum allowed value for the x and y coordinates
   * @return a new Point with the limited coordinates
   */
  public Point limit(Long maxAxial) {
    return new Point(
        Math.min(Math.max(this.x, -maxAxial), maxAxial),
        Math.min(Math.max(this.y, -maxAxial), maxAxial));
  }

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
   * Multiplies the coordinates of this point with another point.
   *
   * <p>This method returns a new Point where each coordinate is the product of the corresponding
   * coordinates of this point and the provided point.
   *
   * @param other the point to multiply with this point
   * @return a new Point with the product of the coordinates
   */
  public Point mul(Point other) {
    return new Point(this.x * other.x, this.y * other.y);
  }

  /**
   * Multiplies the coordinates of this point with a given multiplier.
   *
   * <p>This method returns a new Point where each coordinate is the product of the corresponding
   * coordinates of this point and the provided multiplier.
   *
   * @param multiplier the multiplier to multiply with this point's coordinates
   * @return a new Point with the product of the coordinates and the multiplier
   */
  public Point mul(Long multiplier) {
    return new Point(this.x * multiplier, this.y * multiplier);
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
   * Returns a new Point representing the x-component of this Point.
   *
   * @return a new Point representing the x-component of this Point
   */
  public Point xComp() {
    return new Point(this.x, 0);
  }

  /**
   * Returns a new Point representing the y-component of this Point.
   *
   * @return a new Point representing the y-component of this Point
   */
  public Point yComp() {
    return new Point(0, this.y);
  }

  /**
   * Compares this point with another point for order.
   *
   * <p>defines a natural ordering for points. The ordering is first based on the x-coordinate, and
   * then on the y-coordinate.
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
