package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

public class Exit extends Empty {

  /**
   * Creates a new Exit Tile at the given location
   *
   * @param location the location to create the tile at
   */
  public Exit(Point location) {
    super(location);
  }

  @Override
  public void enter(Entity enteree) {
    if (enteree instanceof Player p) {
      p.win();
    }
    super.enter(enteree);
  }

  public static Exit fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Exit")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    return new Exit(Point.fromJSON(json.get("position")));
  }
}
