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

  void addEntity(Entity toAdd) {
    Tile reqTile =
        getTile(toAdd.getLocation())
            .orElseThrow(
                () -> new IllegalArgumentException("No tile exists at the required coordinates"));
    if (reqTile.getOccupant().isPresent()) {
      throw new IllegalStateException("The required tile is already occupied");
    }
    reqTile.put(toAdd);
  }

  void addTile(Tile tile) {
    if (getTile(tile.getLocation()).isPresent()) {
      throw new IllegalArgumentException("Tile at those coordinates already exists");
    }
    tiles.add(tile);
  }

  Optional<Tile> getTile(Point at) {
    tiles.sort(Tile::compareTo);
    return tiles.parallelStream().filter(t -> t.getLocation().equals(at)).findAny();
  }

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

  Optional<Entity> getEntity(Point at) {
    return getTile(at).flatMap(Tile::getOccupant);
  }

  List<Entity> getEntities() {
    return tiles.stream()
        .map(Tile::getOccupant)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }

  List<Entity> getEntities(Point around, long range) {
    return getTiles(around, range).stream()
        .map(Tile::getOccupant)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }
}
