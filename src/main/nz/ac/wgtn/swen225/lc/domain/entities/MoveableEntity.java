package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;
import nz.ac.wgtn.swen225.lc.domain.tiles.MovementAffecterTile;

/** MoveableEntity is a more advanced version of a {@link Entity} that is capable of moving */
public abstract class MoveableEntity extends AbstractEntity {
  public MoveableEntity(Point location, long individualID) {
    super(location, individualID);
  }

  protected Point lastMove = Point.ORIGIN;

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
    if (!newLoc.canEnter(this)) {
      throw new IllegalArgumentException("Entity may not enter the requested tile");
    }
    oldLoc.leave(this);
    newLoc.enter(this);
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
      throw new IllegalStateException("Unexpected last move encountered");
    }
  }
}
