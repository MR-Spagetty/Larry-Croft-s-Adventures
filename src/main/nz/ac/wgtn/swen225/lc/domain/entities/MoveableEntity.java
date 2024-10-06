package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.tiles.MovementAffectorTile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/** MoveableEntity is a more advanced version of a {@link Entity} that is capable of moving */
public interface MoveableEntity extends Entity {
  Point lastMove();
  /**
   * Moves this entity by the specified amount in the given maze.
   *
   * @param by the amount to move this entity
   * @param in the maze in which to move this entity
   */
  default void move(Point by) {
    Tile oldLoc =
        getMaze()
            .getTile(location())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Maze does not contain tile this entity occupies"));
    if (oldLoc instanceof MovementAffectorTile MET) {
      by = MET.affectMove(this, by);
    }
    Tile newLoc =
        getMaze()
            .getTile(location().add(by))
            .orElseThrow(
                () -> new IllegalArgumentException("requested tile does not exist in maze"));
    if (!newLoc.canEnter(this)) {
      throw new IllegalArgumentException("Entity may not enter the requested tile");
    }
    oldLoc.leave(this);
    newLoc.enter(this);
  }
}
