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

  @Override
  public Point affectMove(MoveableEntity e, Point moveToEffect) {
    return e.lastMove().equals(Point.ORIGIN)?moveToEffect:e.lastMove();
  }

  @Override
  public Point location() {
    return this.location;
  }

  @Override
  public void put(Entity enteree) {
    this.occupant = Optional.of(enteree);
  }

  @Override
  public void enter(Entity enteree) {
    if (this.occupant.isPresent()) {
      throw new IllegalStateException(
          "The entity: %d may not enter this tile".formatted(enteree.getUID()));
    }
    this.occupant = Optional.of(enteree);
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
