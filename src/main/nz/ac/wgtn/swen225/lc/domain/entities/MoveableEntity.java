package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.tiles.MovementAffecterTile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * MoveableEntity is a more advanced version of a {@link Entity} that is capable of moving
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public abstract class MoveableEntity extends AbstractEntity {
  /**
   * creates a new Entity at the given position with the given individual id
   *
   * @param location the position to make the entity at
   * @param individualID the individual id of the entity
   */
  public MoveableEntity(Point location, long individualID) {
    super(location, individualID);
  }

  protected Point lastMove = Point.ORIGIN;

  /**
   * gets the last concious move the entity made
   *
   * @return the last concious move the entity made
   */
  public final Point lastMove() {
    return this.lastMove;
  }

  /**
   * Moves this entity by the specified amount in the given maze.
   *
   * @param by the amount to move this entity
   */
  public final void move(Point by) {
    Tile oldLoc =
        maze()
            .getTile(location())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Maze does not contain tile this entity occupies"));
    if (oldLoc instanceof MovementAffecterTile MET) {
      by = MET.affectMove(this, by);
    }
    if (by.equals(Point.ORIGIN)) {
      return;
    }
    Tile newLoc =
        maze()
            .getTile(location().add(by))
            .orElseThrow(
                () -> new IllegalArgumentException("requested tile does not exist in maze"));
    newLoc.getOccupant().filter(e -> e.canTouch(this)).ifPresent(e -> e.touch(this));
    if (!newLoc.canEnter(this)){return;}
    newLoc.enter(this);
    oldLoc.leave(this);
  }

  /**
   * Gets the direction the entity is facing based on its last concious move
   *
   * @return the facing direction as a PlayerAction for ease of use
   */
  public PlayerAction getFacing() {
    Point move = lastMove().limit(1l);
    if (move.equals(Point.ORIGIN)) {
      return PlayerAction.None;
    } else if (move.equals(new Point(1, 0))) {
      return PlayerAction.Right;
    } else if (move.equals(new Point(-1, 0))) {
      return PlayerAction.Left;
    } else if (move.equals(new Point(0, 1))) {
      return PlayerAction.Up;
    } else if (move.equals(new Point(0, -1))) {
      return PlayerAction.Down;
    } else {
      // will not be reached within normal program
      throw new IllegalStateException("Unexpected last move encountered");
    }
  }
}
