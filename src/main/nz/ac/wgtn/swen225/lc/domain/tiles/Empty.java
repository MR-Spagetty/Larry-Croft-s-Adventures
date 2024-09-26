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
  public boolean canEnter(Entity enteree) {
    // TODO checks with advanced occupants
    return getOccupant().isEmpty();
  }

  @Override
  public void enter(Entity enteree) {
    // TODO handeling of advanced occupants
    if (this.occupant.isPresent()) {
      throw new IllegalStateException(
          "The entity: %l may not enter this tile".formatted(enteree.getUID()));
    }
    this.occupant = Optional.of(enteree);
  }

  @Override
  public void leave(Entity exitee) {
    if (this.occupant.map(e -> e.equals(exitee)).orElse(false)) {
      this.occupant = Optional.empty();
    }
  }
}
