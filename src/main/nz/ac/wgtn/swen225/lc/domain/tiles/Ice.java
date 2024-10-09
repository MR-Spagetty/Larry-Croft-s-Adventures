package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

public class Ice implements MovementAffectorTile {

  private Optional<Entity> occupant = Optional.empty();
  private final Point location;

  /**
   * @param location
   */
  public Ice(Point location) {
    this.location = location;
  }

  /**
   * affects the given movement of the given entity to obey the properties of ice
   *
   * <p>the properties of ice are continued momentum in that once an entity has begun movement in a
   * direction it will continue to move in that direction until it cannot
   */
  @Override
  public Point affectMove(MoveableEntity e, Point moveToEffect) {
    return e.lastMove().equals(Point.ORIGIN) ? moveToEffect : e.lastMove();
  }

  @Override
  public Point location() {
    return this.location;
  }

  @Override
  public void put(Entity enteree) {
    if (this.occupant.isPresent()) {
      throw new IllegalStateException();
    }
    this.occupant = Optional.of(enteree);
    enteree.location(location());
  }

  @Override
  public void enter(Entity enteree) {
    if (!canEnter(enteree)) {
      throw new IllegalStateException(
          "The entity: %d may not enter this tile".formatted(enteree.getUID()));
    }
    put(enteree);
  }

  @Override
  public Optional<Entity> getOccupant() {
    return this.occupant;
  }

  @Override
  public void leave(Entity exitee) {
    if (this.occupant.map(e -> e.equals(exitee)).orElse(false)) {
      this.occupant = Optional.empty();
    }
  }
}
