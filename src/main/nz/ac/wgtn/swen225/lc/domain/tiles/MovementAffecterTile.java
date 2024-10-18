package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

/**
 * Interface for use by tiles that affect the movement of an entity
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public abstract class MovementAffecterTile extends AbstractTile {

  /**
   * creates a new MAT at the given location
   *
   * @param location hte location to create the MAT at
   */
  public MovementAffecterTile(Point location) {
    super(location);
  }

  /**
   * affects the given movement of the given entity to obey the tile
   *
   * @param e the entity to use as reference for the modification
   * @param moveToEffect the move to modify
   * @return the modified move
   */
  public abstract Point affectMove(MoveableEntity e, Point moveToEffect);
}
