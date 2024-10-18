package nz.ac.wgtn.swen225.lc.domain.entities.items;

import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.AbstractEntity;
import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONSerializable;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * Item is a an abstract class that impliments everything common to all items
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public abstract class Item extends AbstractEntity implements JSONSerializable<Item> {
  /**
   * creates a new item at the given position
   *
   * @param location
   * @param individualID
   */
  public Item(Point location, long individualID) {
    super(location, individualID);
  }

  @Override
  public void tick(long tick) {}

  @Override
  public final boolean canTouch(Entity touchee) {
    return touchee instanceof Player;
  }

  @Override
  public final void touch(Entity touchee) {
    if (touchee instanceof Player p) {
      maze().getTile(location()).ifPresent(t -> t.leave(this));
      p.pickUp(this);
    }
    throw new IllegalArgumentException("Given entity may not touch items");
  }

  protected abstract Item item(Point location, long id);

  @Override
  public Item fromJson(JSONType json) {
    JSONObject data = (JSONObject) json;
    String expectedType = getClass().getName();
    if (!((JSONString) data.get("type"))
        .get()
        .equals(expectedType.substring(expectedType.lastIndexOf(',') + 1))) {
      throw new IllegalArgumentException(
          "Incorrect data given expected Conveyor got: " + data.get("type"));
    }
    return item(Point.fromJSON(data.get("position")), Entity.idFromJSON(data));
  }
}
