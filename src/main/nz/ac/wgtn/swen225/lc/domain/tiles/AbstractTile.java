package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

/**
 * AbstractTile is a abstract class that implements the fields and methods used by all nonWall tiles
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public abstract class AbstractTile implements Tile {

  private final Point location;

  /**
   * creates a new tile at the given position
   *
   * @param location the position to create the tile at
   */
  public AbstractTile(Point location) {
    this.location = location;
  }

  private Optional<Entity> occupant = Optional.empty();

  @Override
  public final Point location() {
    return this.location;
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return getOccupant().isEmpty();
  }

  @Override
  public void enter(Entity enteree) {
    if (!canEnter(enteree)) {
      throw new IllegalArgumentException(
          "The entity: %d may not enter this tile".formatted(enteree.getUID()));
    }
    put(enteree);
  }

  @Override
  public final void put(Entity enteree) {
    if (getOccupant().isPresent()) {
      throw new IllegalStateException(
          "The entity: %d may not be put in this tile as it is already occupied"
              .formatted(enteree.getUID()));
    }
    this.occupant = Optional.of(enteree);
    enteree.location(location());
  }

  @Override
  public final Optional<Entity> getOccupant() {
    return this.occupant;
  }

  @Override
  public final void leave(Entity exitee) {
    if (getOccupant().map(e -> e == exitee).orElse(false)) {
      this.occupant = Optional.empty();
    }
  }
}
