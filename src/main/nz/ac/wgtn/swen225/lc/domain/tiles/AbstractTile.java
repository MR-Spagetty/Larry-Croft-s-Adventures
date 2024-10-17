package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

/** Represents a tile in the game world. */
public abstract class AbstractTile implements Tile {

  private final Point location;
  /**
   * @param location
   */
  public AbstractTile(Point location) {
    this.location = location;
  }

  private Optional<Entity> occupant = Optional.empty();

  /**
   * Returns the location of this tile in the game world.
   *
   * @return the location of this tile
   */
  public final Point location() {
    return this.location;
  }

  /**
   * Determines whether the specified entity can enter this tile.
   *
   * @param enteree the entity to check for entrance
   * @return {@code true} if the entity can enter this tile, {@code false} otherwise
   */
  public boolean canEnter(Entity enteree) {
    return getOccupant().isEmpty();
  }

  /**
   * Allows the specified entity to enter this tile.
   *
   * @param enteree the entity to enter this tile
   * @throws IllegalStateException if the tile may not be occupied by the entity
   * @throws UnsupportedOperationException if the tile may never be occupied
   */
  public void enter(Entity enteree) {
    if (!canEnter(enteree)) {
      throw new IllegalStateException(
          "The entity: %d may not enter this tile".formatted(enteree.getUID()));
    }
    put(enteree);
  }

  /**
   * similar to {@link #enter(Entity)} but does not execute any additional actions
   *
   * @param enteree the entity to put in this tiles
   * @throws IllegalStateException if the tile may not be occupied by the entity
   * @throws UnsupportedOperationException if the tile may never be occupied
   */
  public final void put(Entity enteree) {
    if (this.occupant.isPresent()) {
      throw new IllegalStateException(
          "The entity: %d may not be put in this tile as it is already occupied"
              .formatted(enteree.getUID()));
    }
    this.occupant = Optional.of(enteree);
    enteree.location(location());
  }

  /**
   * Returns the entity currently occupying this tile, if any.
   *
   * @return an {@link Optional} containing the entity currently occupying this tile, or an empty
   *     {@link Optional} if the tile is empty
   */
  public final Optional<Entity> getOccupant() {
    return this.occupant;
  }

  /**
   * Allows the specified entity to leave this tile.
   *
   * <p>This method removes the entity from the tile if the entity was the occupant of this tile,
   * indicating that the entity has moved to another tile in the game world. If the entity is not
   * currently occupying this tile, this method does nothing.
   *
   * @param exitee the entity to leave this tile
   */
  public final void leave(Entity exitee) {
    if (getOccupant().map(e -> e == exitee).orElse(false)) {
      this.occupant = Optional.empty();
    }
  }
}
