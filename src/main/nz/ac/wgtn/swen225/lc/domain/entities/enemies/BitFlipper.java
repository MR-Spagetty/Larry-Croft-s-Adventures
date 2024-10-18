package nz.ac.wgtn.swen225.lc.domain.entities.enemies;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.BiFunction;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.Item;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

public class BitFlipper extends Enemy implements JSONSerializable<BitFlipper> {

  /**
   * creates a new BitFlipper enemy at the given location with the given individual id
   *
   * @param location the location to create the enemy at
   * @param individualID the individual id of the enemy
   */
  public BitFlipper(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  protected void doBehaviour(long tick) {
    move(toPlayer().stream().findFirst().orElse(location()).sub(location()));
  }

  private List<Point> toPlayer() {
    return aStar(location(), maze().getPlayer().location(), maze(), BitFlipper::new);
  }

  private void returnYoinked(Item item) {
    Optional<Point> returnTo =
        aStar(
                location(),
                item.location(),
                maze(),
                (p, id) ->
                    new Item(p, id) {
                      @Override
                      protected Item item(Point location, long id) {
                        throw new UnsupportedOperationException();
                      }
                    })
            .stream()
            .reduce((a, b) -> b);
    if (returnTo.isEmpty()) {
      maze().getPlayer().pickUp(item);
    } else {
      maze().getTile(returnTo.get()).get().put(item);
    }
  }

  @Override
  public void touch(Entity touchee) {
    super.touch(touchee);
    Player p = (Player) touchee;
    if (!p.getInventory().isEmpty()) {
      int indexToYoink = behaviourDecider.nextInt(p.getInventory().size());
      Item expected = p.getInventory().get(indexToYoink);
      Optional<Item> yoinked = p.lose(new ItemChooser(indexToYoink));
      assert yoinked.isPresent();
      assert yoinked.get() == expected;
      returnYoinked(expected);
    }
  }

  /** value for use in allowing the a* search to attempt to recover from a local minimum weight */
  private static final double CLOSEST_REP_THRESHOLD = 10;

  /**
   * finds the shortest path to the closest possible point to the goal using slightly modified a*
   *
   * <p>the modifications made are no visiting is for getting the
   *
   * @param start the start of the ath
   * @param goal the point the path is trying to reach
   * @param maze the maze to assemble the path within
   * @param refSup supplies the entity to use as reference, this entity should not be a real entity
   *     that is in use in the maze and may not have the maze already set
   * @return the path to the reachable point closest to the goal
   */
  public static List<Point> aStar(
      Point start, Point goal, Maze maze, BiFunction<Point, Long, Entity> refSup) {

    Entity ref = refSup.apply(Point.ORIGIN, 0l);
    ref.maze(maze);
    PriorityQueue<Path> queue = new PriorityQueue<>();
    queue.add(new Path(start, goal));
    Path closest = queue.peek();
    do {
      Path curr = queue.poll();
      if (curr.estimatedWeight() <= closest.estimatedWeight()) {
        // recording current closest reachable point
        closest = curr;
      } else if (curr.estimatedWeight() - CLOSEST_REP_THRESHOLD > closest.estimatedWeight()
          && curr.newLoc().equals(closest.newLoc())) {
        // break out of the search of a local minimum cannot be escaped/is true minimum
        break;
      }
      // calculate next set of paths
      ref.location(curr.newLoc());
      if (ref instanceof BitFlipper me && curr.prev().isPresent()) {
        me.lastMove = curr.newLoc().sub(curr.prev().get().newLoc());
      }
      dirs.parallelStream()
          .<Point>map(
              d -> {
                return maze.getTile(ref.location()).orElseThrow()
                            instanceof MovementAffecterTile mat
                        && ref instanceof MoveableEntity me
                    ? mat.affectMove(me, d)
                    : d;
              })
          .filter(d -> !d.equals(Point.ORIGIN))
          .map(d -> ref.location().add(d))
          .filter(p -> maze.getTile(p).isPresent())
          .filter(p -> maze.getTile(p).get().canEnter(ref))
          .map(p -> new Path(curr, p, getWeightOf(maze.getTile(p).get()), goal));
    } while (!queue.isEmpty() && !queue.parallelStream().anyMatch(p -> p.newLoc().equals(goal)));
    return queue.parallelStream()
        .filter(p -> p.newLoc().equals(goal))
        .findAny()
        .orElse(closest)
        .toList();
  }

  private static double getWeightOf(Tile tile) {
    return switch (tile) {
      case Fire f -> 1.5;
      case Water w -> w.filled() ? 1 : 1.5;
      default -> 1;
    };
  }

  @Override
  public BitFlipper fromJson(JSONType json) {
    JSONObject data = (JSONObject) json;
    String type = ((JSONString) data.get("type")).get();
    if (!type.equals("BitFlipper")) {
      throw new IllegalArgumentException("Incorrect data given expected BitFlipper got: " + type);
    }
    return new BitFlipper(Point.fromJSON(data.get("Position")), Entity.idFromJSON(data));
  }

  /**
   * statically deserializes a BitFlipper enemy for documentation see {@link #fromJson(JSONType)}
   */
  public static BitFlipper fromJSON(JSONType json) {
    final BitFlipper ref = new BitFlipper(Point.ORIGIN, 0);
    return ref.fromJson(json);
  }
}
