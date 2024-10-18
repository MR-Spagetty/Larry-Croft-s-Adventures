package nz.ac.wgtn.swen225.lc.domain.tiles;

import static nz.ac.wgtn.swen225.lc.domain.PlayerAction.*;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.SuctionBoots;
import nz.ac.wgtn.swen225.lc.persistency.JSONLong;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

/**
 * Conveyor tiles are tiles that push the entity occupying them in the direction the tile is facing,
 * while on the tile entities may only consciously move perpendicularly with the tile any other
 * attempted concious movement will be nullified
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public class Conveyor extends MovementAffecterTile {
  /**
   * the possible facing directions for the conveyor tile by index: 0 = Up, 1 = Right, 2 = Down, 3=
   * Left
   */
  public static final PlayerAction[] DIRS = {Up, Right, Down, Left};

  private final PlayerAction targetDir;

  /**
   * creates a new Conveyor tile at the given position facing in the given direction
   *
   * @param location the position to make the tile at
   * @param type the direction for it to be facing see {@link Conveyor#DIRS} for numbers to use for
   *     what direction
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
    if (e instanceof Player p
        && p.getInventory().parallelStream().anyMatch(i -> i instanceof SuctionBoots)) {
      return moveToEffect;
    }
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

  /**
   * gets the direction this tile is facing
   *
   * @return the direction this tile is facing as a PlayerAction
   */
  public PlayerAction getFacing() {
    return this.targetDir;
  }

  /**
   * Deserializes a Conveyor tile from the given JSON data
   *
   * @param json the data to use
   * @return the deserialized tile
   * @throws IllegalArgumentException if the given data is not correct for a conveyor tile
   */
  public static Conveyor fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Conveyor")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    if (!(json.get("type") instanceof JSONLong)) {
      throw new IllegalArgumentException(
          "Expected long type got: " + json.get("type").getClass().getName());
    }
    return new Conveyor(
        Point.fromJSON(json.get("position")),
        Integer.parseInt("" + (((JSONLong) json.get("type")).get())));
  }
}
