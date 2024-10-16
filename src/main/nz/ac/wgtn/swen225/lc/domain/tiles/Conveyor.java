package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.persistency.JSONLong;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

public class Conveyor extends MovementAffecterTile {
  public static final PlayerAction[] DIRS = new PlayerAction[] {Up, Right, Down, Left};
  private final PlayerAction targetDir;

  /**
   * @param targetDir
   */
  public Conveyor(Point location, int type) {
    super(location);
    this.targetDir = DIRS[type];
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
    if (this.targetDir == Up || this.targetDir == Down) {
      allowed = moveToEffect.xComp();
    } else {
      allowed = moveToEffect.yComp();
    }
    return allowed.add(this.targetDir.offset).limit(1l);
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return super.canEnter(enteree)
        && !enteree.location().equals(location().add(this.targetDir.offset));
  }

  public PlayerAction getFacing() {
    return this.targetDir;
  }

  public static Conveyor fromJson(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Conveyor")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    if (!(json.get("type") instanceof JSONLong)) {
      throw new IllegalArgumentException(
          "Expected long type got: " + json.get("type").getClass().getName());
    }
    return new Conveyor(
        Point.fromJSON(json.get("position")), Integer.parseInt(""+(((JSONLong) json.get("type")).get())));
  }
}
