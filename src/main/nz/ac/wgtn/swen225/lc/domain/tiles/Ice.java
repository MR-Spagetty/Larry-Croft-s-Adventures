package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.domain.entities.items.IceBoots;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

/**
 * Ice is a MovementAffecterTile that causes the occupant to continue moving in the direction is
 * last consciously moved until no longer possible or the entity is no longer on an ice tile to
 * prevent soft locking if an entity is stopped on ice it may conciously move in any direction
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com> 300651923
 */
public class Ice extends MovementAffecterTile {

  /**
   * creates a new Ice tile at the given position
   *
   * @param location the position to create the tile at
   */
  public Ice(Point location) {
    super(location);
  }

  /**
   * affects the given movement of the given entity to obey the properties of ice
   *
   * <p>the properties of ice are continued momentum in that once an entity has begun movement in a
   * direction it will continue to move in that direction until it cannot
   */
  @Override
  public Point affectMove(MoveableEntity e, Point moveToEffect) {
    if (e instanceof Player p
        && p.getInventory().parallelStream().anyMatch(i -> i instanceof IceBoots)) {
      return moveToEffect;
    }
    return e.lastMove().equals(Point.ORIGIN) ? moveToEffect : e.lastMove();
  }

  /**
   * Deserializes an Ice tile from the given json data
   *
   * @param json the data to use
   * @return the deserialized tile
   * @throws IllegalArgumentException if the data is incorrect for an ice tile
   */
  public static Ice fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Ice")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    return new Ice(Point.fromJSON(json.get("position")));
  }
}
