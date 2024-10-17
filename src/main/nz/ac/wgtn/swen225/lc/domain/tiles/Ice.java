package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.MoveableEntity;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

public class Ice extends MovementAffecterTile {

  /**
   * @param location
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
    return e.lastMove().equals(Point.ORIGIN) ? moveToEffect : e.lastMove();
  }

  public static Ice fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Ice")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    return new Ice(Point.fromJSON(json.get("position")));
  }
}
