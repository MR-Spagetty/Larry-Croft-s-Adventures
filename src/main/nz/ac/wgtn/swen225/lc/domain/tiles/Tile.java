package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;

/** Represents a tile in the game world. */
public interface Tile extends Comparable<Tile> {

  /**
   * Returns the location of this tile in the game world.
   *
   * @return the location of this tile
   */
  Point location();

  /**
   * Determines whether the specified entity can enter this tile.
   *
   * @param enteree the entity to check for entrance
   * @return {@code true} if the entity can enter this tile, {@code false} otherwise
   */
  default boolean canEnter(Entity enteree) {
    return false;
  }

  /**
   * Allows the specified entity to enter this tile.
   *
   * @param enteree the entity to enter this tile
   * @throws IllegalStateException if the tile may not be occupied by the entity
   * @throws UnsupportedOperationException if the tile may never be occupied
   */
  default void enter(Entity enteree) {
    throw new UnsupportedOperationException("This tile may not be occupied");
  }

  /**
   * similar to {@link #enter(Entity)} but does not execute any additional actions
   *
   * @param enteree the entity to put in this tiles
   * @throws IllegalStateException if the tile may not be occupied by the entity
   * @throws UnsupportedOperationException if the tile may never be occupied
   */
  default void put(Entity enteree) {
    throw new UnsupportedOperationException("This tile may not be occupied");
  }

  /**
   * Returns the entity currently occupying this tile, if any.
   *
   * @return an {@link Optional} containing the entity currently occupying this tile, or an empty
   *     {@link Optional} if the tile is empty
   */
  default Optional<Entity> getOccupant() {
    return Optional.empty();
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
  void leave(Entity exitee);

  /**
   * Compares this tile with the specified tile for order.
   *
   * <p>This method is used for sorting tiles in a game world. It compares the locations of the
   * tiles, using the natural ordering of {@link Point}.
   *
   * @param other the tile to be compared with this tile
   * @return an integer representing the ordering of the tiles
   * @see Point#compareTo(Point)
   */
  @Override
  default int compareTo(Tile other) {
    return location().compareTo(other.location());
  }
}
