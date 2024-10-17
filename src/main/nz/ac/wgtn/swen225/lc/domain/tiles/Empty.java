package nz.ac.wgtn.swen225.lc.domain.tiles;


import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

public class Empty extends AbstractTile {

  /**
   * creates a new Empty tile at the given location
   *
   * @param location the location to create the tile at
   */
  public Empty(Point location) {
    super(location);
  }

  public static Empty fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Empty")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    return new Empty(Point.fromJSON(json.get("position")));
  }
}
