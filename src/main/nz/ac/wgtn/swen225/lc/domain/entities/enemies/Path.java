package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import nz.ac.wgtn.swen225.lc.domain.Point;

/**
 * Path is a record for aid in calculating and assembling a* paths
 *
 * @param prev Optional of the previous portion of the path if empty this portion is the first
 *     portion
 * @param newLoc the point reached at this point on the path
 * @param weight the weight of this portion of the path
 * @param goal the goal of the path
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public record Path(Optional<Path> prev, Point newLoc, double weight, Point goal)
    implements Comparable<Path> {

  /**
   * creates a new Path object with the given data
   *
   * @param prev the previous portion of the path
   * @param newLoc the location that will have been reached at this point in the path
   * @param weight the weight/cost of this portion of the path
   * @param goal the goal point of the path for calculating estimated weight
   */
  public Path(Path prev, Point newLoc, double weight, Point goal) {
    this(Optional.ofNullable(prev), newLoc, weight, goal);
  }

  /**
   * creates a new Path object to represent the beginning of the path
   *
   * @param start the start location of the path
   * @param goal the goal point of the path for calculating estimated weight
   */
  public Path(Point start, Point goal) {
    this(Optional.empty(), start, 0, goal);
  }

  /**
   * Calculates the estimated weight of this path so far
   *
   * <p>the estimated weight is the total known definitive weight + the euclidean distance between
   * the end of the path so far and the goal
   *
   * @return the calculated estimated weight
   */
  public double estimatedWeight() {
    return totalWeight() + newLoc().dist(goal());
  }

  /**
   * calculates the total known definitive weight of this path so far
   *
   * <p>calculated as the individual weight of this portion + the total weight of the previous
   * portion, the weight of this portion is additionally multiplied by 2^(occurrences of of this
   * location in the path - 1) to help escape local minimums
   *
   * @return the total known definitive weight
   */
  public double totalWeight() {
    return Math.pow(2, stream().parallel().filter(p -> p.equals(newLoc())).count() - 1)
            * this.weight
        + prev.map(Path::totalWeight).orElse(0.);
  }

  @Override
  public int compareTo(Path other) {
    return Double.compare(estimatedWeight(), other.estimatedWeight());
  }

  /**
   * converts this path to an un-nested stream for easy use
   *
   * <p>represents the path as an ordered stream from start to end
   *
   * @return the stream representation of the path
   * @see #toList()
   */
  public Stream<Point> stream() {
    return Stream.of(this)
        .mapMulti(
            (m, cons) -> {
              m.prev().map(Path::stream).orElse(Stream.of()).forEach(cons);
              cons.accept(m.newLoc());
            });
  }

  /**
   * converts this path to a List for simple use
   *
   * @return the list representation of this path
   * @see #stream()
   */
  public List<Point> toList() {
    return stream().toList();
  }
}