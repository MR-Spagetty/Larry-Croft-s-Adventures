package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;

/**
 * Walls are basic tiles that may not be occupied
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public record Wall(Point location) implements Tile {

  @Override
  public void leave(Entity exitee) {
    return;
  }

  @Override
  public boolean canEnter(Entity enteree) {
    return false;
  }

  @Override
  public void enter(Entity enteree) {
    throw new UnsupportedOperationException("This tile may never be occupied");
  }

  @Override
  public void put(Entity enteree) {
    throw new UnsupportedOperationException("This tile may never be occupied");
  }

  @Override
  public Optional<Entity> getOccupant() {
    return Optional.empty();
  }

  public static Wall fromJSON(JSONObject json) {
    if (!((JSONString) json.get("tile")).get().equals("Wall")) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + json.get("Tile"));
    }
    return new Wall(Point.fromJSON(json.get("position")));
  }
}
