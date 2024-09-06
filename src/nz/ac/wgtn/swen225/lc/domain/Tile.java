package nz.ac.wgtn.swen225.lc.domain;

import java.util.Optional;

/** Represents a tile in the game world. */
public interface Tile {

  /**
   * Returns the location of this tile in the game world.
   *
   * @return the location of this tile
   */
  Point getLocation();

  /**
   * Determines whether the specified entity can enter this tile.
   *
   * @param enteree the entity to check for entrance
   * @return {@code true} if the entity can enter this tile, {@code false} otherwise
   */
  boolean canEnter(Entity enteree);

  /**
   * Allows the specified entity to enter this tile.
   *
   * @param enteree the entity to enter this tile
   */
  void enter(Entity enteree);

  /**
   * Returns the entity currently occupying this tile, if any.
   *
   * @return an {@link Optional} containing the entity currently occupying this tile, or an empty
   *     {@link Optional} if the tile is empty
   */
  default Optional<Entity> getOccupant() {
    return Optional.empty();
  }
}
