package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

public class Conveyor implements MovementAffectorTile {
  private static final PlayerAction[] DIRS = new PlayerAction[] {Up, Right, Down, Left};
  public final Point targetDir;
  private Optional<Entity> occupant = Optional.empty();
  private final Point location;

  /**
   * @param targetDir
   */
  public Conveyor(Point location, int type) {
    this.location = location;
    this.targetDir = DIRS[type].offset;
  }

  @Override
  public Point location() {
    return location;
  }

  /**
   * affects the given movement of the given entity to obey this conveyor
   *
   * <p>Entities may only consciously move perpendicular to this tile
   */
  @Override
  public Point affectMove(MoveableEntity e, Point moveToEffect) {
    Point allowed;
    // Entity's "concious" movement may only be perpendicular to this conveyor
    if (this.targetDir.equals(Up.offset) || this.targetDir.equals(Down.offset)) {
      allowed = moveToEffect.xComp();
    } else {
      allowed = moveToEffect.yComp();
    }
    return allowed.add(this.targetDir).limit(1l);
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return getOccupant().isEmpty() && !enteree.location().equals(location().add(targetDir));
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
