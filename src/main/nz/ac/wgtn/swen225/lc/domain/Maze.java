package nz.ac.wgtn.swen225.lc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Maze {
  private List<Tile> tiles = new ArrayList<>();
  public final long maxTicks;

  /**
   * @param maxTicks
   */
  public Maze(long maxTicks) {
    this.maxTicks = maxTicks;
  }

  /**
   * tries to add the given entity to the maze
   *
   * @param toAdd the entity to add
   * @throws IllegalArgumentException if there is no tile at the coordinates of the given entity
   */
  void addEntity(Entity toAdd) {
    Tile reqTile =
        getTile(toAdd.getLocation())
            .orElseThrow(
                () -> new IllegalArgumentException("No tile exists at the required coordinates"));
    reqTile.put(toAdd);
  }

  /**
   * tries to add the given tile to the maze
   *
   * @param tile the tile to add
   * @throws IllegalArgumentException if there is already a tile at the coordinates of the given
   *     tile
   */
  void addTile(Tile tile) {
    if (getTile(tile.getLocation()).isPresent()) {
      throw new IllegalArgumentException("Tile at those coordinates already exists");
    }
    tiles.add(tile);
  }

  /**
   * tries to get the tile at the given coordinates
   *
   * @param at the coordinates to get the tile at
   * @return an {@link Optional} containing the tile at the requested location, or an empty {@link
   *     Optional} if there is no such tile
   */
  Optional<Tile> getTile(Point at) {
    tiles.sort(Tile::compareTo);
    return tiles.parallelStream().filter(t -> t.getLocation().equals(at)).findAny();
  }

  /**
   * gets the tiles within the specified region
   *
   * @param around the center point of the region
   * @param range the range (inclusive ±) the region occupies in a square around around
   * @return the tiles within that region
   */
  List<Tile> getTiles(Point around, long range) {
    tiles.sort(Tile::compareTo);
    return tiles.parallelStream()
        .filter(
            t -> {
              Point p = t.getLocation();
              return (p.x() >= around.x() - range)
                  && (p.x() <= around.x() + range)
                  && (p.y() >= around.y() - range)
                  && (p.y() <= around.y() + range);
            })
        .sorted()
        .toList();
  }

  /**
   * tries to get the entity at the given point
   *
   * @param at the point to get the entity from
   * @return an {@link Optional} containing the entity currently at the requested location, or an
   *     empty {@link Optional} if there is no such entity
   */
  Optional<Entity> getEntity(Point at) {
    return getTile(at).flatMap(Tile::getOccupant);
  }

  /**
   * gets an unmodifiable list of all the entities in the maze
   *
   * @return the entities in the maze
   */
  List<Entity> getEntities() {
    return tiles.stream()
        .map(Tile::getOccupant)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }

  /**
   * get all the entities in the specified region
   *
   * @param around the center point of the region
   * @param range the range (inclusive ±) the region occupies in a square around around
   * @return the entities within that region
   */
  List<Entity> getEntities(Point around, long range) {
    return getTiles(around, range).stream()
        .map(Tile::getOccupant)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }
}
