package nz.ac.wgtn.swen225.lc.domain.entities;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;

public interface Enemy extends MoveableEntity {
  @Override
  default boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  default void touch(Entity touchee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'touch'");
  }

  default PlayerAction getFacing() {
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
