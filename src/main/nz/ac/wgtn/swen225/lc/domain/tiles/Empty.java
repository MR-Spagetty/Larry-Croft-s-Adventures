package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

public class Empty implements Tile {

  /**
   * creates a new Empty tile at the given location
   *
   * @param location the location to create the tile at
   */
  public Empty(Point location) {
    this.location = location;
  }

  private Optional<Entity> occupant = Optional.empty();
  private final Point location;

  @Override
  public Point location() {
    return location;
  }

  @Override
  public void put(Entity enteree) {
    if (getOccupant().isPresent()) {
      throw new IllegalStateException("Tile already occupied");
    }
    this.occupant = Optional.of(enteree);
    enteree.location(location());
  }

  @Override
  public boolean canEnter(Entity enteree) {
    // TODO checks with advanced occupants
    return getOccupant().isEmpty();
  }

  @Override
  public void enter(Entity enteree) {
    // TODO handeling of advanced occupants
    if (this.occupant.isPresent()) {
      throw new IllegalStateException(
          "The entity: %d may not enter this tile".formatted(enteree.getUID()));
    }
    put(enteree);
  }

  @Override
  public Optional<Entity> getOccupant() {
    return this.occupant;
  }

  @Override
  public void leave(Entity exitee) {
    if (this.occupant.map(e -> e.equals(exitee)).orElse(false)) {
      this.occupant = Optional.empty();
    }
  }
}
