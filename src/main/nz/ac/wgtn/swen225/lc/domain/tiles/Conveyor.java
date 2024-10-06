package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;

public class Conveyor implements MovementAffectorTile {
  private static final PlayerAction[] DIRS = new PlayerAction[] {Up, Right, Down, Left};
  private Point targetDir;

  /**
   * @param targetDir
   */
  public Conveyor(int type) {
    this.targetDir = DIRS[type].offset;
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
  public void enter(Entity enteree) {
    // TODO Auto-generated method stub

  }

  @Override
  public Optional<Entity> getOccupant() {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public void leave(Entity exitee) {
    // TODO Auto-generated method stub

  }

  @Override
  public Point location() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void put(Entity enteree) {
    // TODO Auto-generated method stub

  }
}
