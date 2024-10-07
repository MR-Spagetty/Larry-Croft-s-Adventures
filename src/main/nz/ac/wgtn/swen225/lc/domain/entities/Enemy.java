package nz.ac.wgtn.swen225.lc.domain.entities;

public interface Enemy extends MoveableEntity{
  @Override
  default boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  default void touch(Entity touchee) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'touch'");
  }
}
