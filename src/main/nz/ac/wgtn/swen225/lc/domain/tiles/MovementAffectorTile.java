package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

/** Interface for use by tiles that affect the movement of an entity */
public interface MovementAffectorTile extends Tile {

  /**
   * affects the given movement of the given entity to obey the tile
   *
   * @param e the entity to use as reference for the modification
   * @param moveToEffect the move to modify
   * @return the modified move
   */
  Point affectMove(MoveableEntity e, Point moveToEffect);

  @Override
  public Point location();

  @Override
  public void put(Entity enteree);

  @Override
  public default boolean canEnter(Entity enteree) {
    // TODO checks with advanced occupants
    return getOccupant().isEmpty();
  }

  @Override
  public void enter(Entity enteree);

  @Override
  public Optional<Entity> getOccupant();

  @Override
  public void leave(Entity exitee);
}
