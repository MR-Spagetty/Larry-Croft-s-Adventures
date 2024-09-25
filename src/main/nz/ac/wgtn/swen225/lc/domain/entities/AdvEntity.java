package nz.ac.wgtn.swen225.lc.domain.entities;

/** AdvEntity is a more advanced version of a {@link Entity} that is capable of being touched */
public interface AdvEntity extends Entity {
  /**
   * check if the given entity can touch this entity
   *
   * @param touchee the entity to touch this entity
   * @return whether this entity can be touched
   */
  public boolean canTouch(Entity touchee);

  /**
   * touches this entity as the given entity
   *
   * @param touchee the entity to execute the touch as
   */
  public void touch(Entity touchee);
}
